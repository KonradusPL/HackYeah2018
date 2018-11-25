package com.konradpekala.hackyeah2018.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.AudioManager.RINGER_MODE_SILENT
import android.media.AudioManager.RINGER_MODE_VIBRATE
import android.os.Build
import android.os.IBinder
import android.os.VibrationEffect
import android.os.Vibrator
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.util.Log
import com.estimote.proximity_sdk.api.EstimoteCloudCredentials
import com.estimote.proximity_sdk.api.ProximityObserver
import com.estimote.proximity_sdk.api.ProximityObserverBuilder
import com.konradpekala.hackyeah2018.ui.main.MainActivity
import com.konradpekala.hackyeah2018.R
import com.konradpekala.hackyeah2018.data.network.ServerNetworking
import com.konradpekala.hackyeah2018.data.repository.CardRepository
import com.konradpekala.hackyeah2018.utils.BeaconUtils
import com.konradpekala.hackyeah2018.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.toast
import java.util.*
import kotlin.collections.HashMap

class BeaconService: Service(), BeaconUtils.BeaconListener{

    val CHANNEL_DEFAULT_IMPORTANCE = "asdasdasd"

    private val cloudCredentials = EstimoteCloudCredentials("hackyeah18-9k4",
        "27fbdd40ccf2727d6ec2dcb7ec16ce19")

    private var mObservationHandler: ProximityObserver.Handler? = null

    val compositeDisposable = CompositeDisposable()

    var notificationManager: NotificationManagerCompat? = null

    override fun onCreate() {
        super.onCreate()
        notificationManager = NotificationManagerCompat.from(this)
        BeaconUtils.listener = this

        val notification: Notification = NotificationCompat.Builder(this, "")
            .setContentTitle("Scanning")
            .setContentText("Scanning beacons in progress...")
            .setSmallIcon(R.drawable.ic_rss_feed_white_24dp)
            .build()

        startForeground(Random().nextInt(1000), notification)

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val proximityObserver = ProximityObserverBuilder(applicationContext, cloudCredentials)
            .withBalancedPowerMode()
            .onError { throwable: Throwable ->  Log.d("Beacons",throwable.toString()) }
            .build()

        mObservationHandler = proximityObserver.startObserving(BeaconUtils.beaconZones)

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mObservationHandler?.stop()
        compositeDisposable.clear()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onEnterZone(tag: String) {
        Log.d("onEnterZone",tag)

        val webApi = ServerNetworking.webApi

        compositeDisposable.add(webApi.getPrice(tag)
            .subscribeOn(SchedulerProvider.io())
            .observeOn(SchedulerProvider.ui())
            .subscribe({t: HashMap<String, Any>? ->
                val value = t?.get("value").toString() ?: ""
                showPaymentNotif(value, tag)
            },{t: Throwable? ->
                Log.d("onEnterZone",t.toString())
            }))

    }

    fun showPaymentNotif(price: String, tag: String){
        val notifId = Random().nextInt(100000)

        CardRepository.currentPrice = price
        CardRepository.currentBeacon = tag

        val yesIntent = Intent(this, MyBroadcastReceiver::class.java).apply {
            action = "yes"
            putExtra("notifId",notifId)
        }
        val yesPendingIntent: PendingIntent =
            PendingIntent.getBroadcast(this,Random().nextInt(100000), yesIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val noIntent = Intent(this, MyBroadcastReceiver::class.java).apply {
            action = "no"
            putExtra("notifId",notifId)
        }
        val noPendingIntent: PendingIntent =
            PendingIntent.getBroadcast(this, Random().nextInt(100000), noIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_DEFAULT_IMPORTANCE)
            .setContentTitle("Payment confirmation")
            .setContentText("Price: $price")
            .setSmallIcon(R.drawable.ic_rss_feed_white_24dp)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .addAction(R.drawable.ic_add_white_24dp,"PAY",yesPendingIntent)
            .addAction(R.drawable.ic_cancel_black_24dp,"SPLITE",noPendingIntent)

        notificationManager?.notify(notifId, notificationBuilder.build())

        makeSound()
    }

    private fun makeSound(){
        val audio = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        val mode = audio.ringerMode
        Log.d("RINGER_MODE",mode.toString())
        Log.d("RINGER_MODE_NORMAL",AudioManager.RINGER_MODE_NORMAL.toString())
        Log.d("RINGER_MODE_SILENT", RINGER_MODE_SILENT.toString())
        Log.d("RINGER_MODE_VIBRATE", RINGER_MODE_VIBRATE.toString())

        if (vibrator.hasVibrator() &&(mode == AudioManager.RINGER_MODE_NORMAL || mode == AudioManager.MODE_RINGTONE)) {
            val mVibratePattern = longArrayOf(0, 400, 200, 400)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val vibrationEffect = VibrationEffect.createWaveform(mVibratePattern,-1)
                vibrator.vibrate(vibrationEffect)
            } else {
                vibrator.vibrate(mVibratePattern,-1)

            }
        }
    }

}