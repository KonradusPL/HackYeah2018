package com.konradpekala.hackyeah2018.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.estimote.proximity_sdk.api.EstimoteCloudCredentials
import com.estimote.proximity_sdk.api.ProximityObserver
import com.estimote.proximity_sdk.api.ProximityObserverBuilder
import com.konradpekala.hackyeah2018.ui.main.MainActivity
import com.konradpekala.hackyeah2018.R
import com.konradpekala.hackyeah2018.utils.BeaconUtils
import org.jetbrains.anko.toast

class BeaconService: Service(){

    private val cloudCredentials = EstimoteCloudCredentials("hackyeah18-9k4",
        "27fbdd40ccf2727d6ec2dcb7ec16ce19")

    private var mObservationHandler: ProximityObserver.Handler? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        toast("onStartCommand")
        val pendingIntent: PendingIntent =
            Intent(this, MainActivity::class.java).let { notificationIntent ->
                PendingIntent.getActivity(this, 0, notificationIntent, 0)
            }

        val proximityObserver = ProximityObserverBuilder(applicationContext, cloudCredentials)
            .withBalancedPowerMode()
            .withEstimoteSecureMonitoringDisabled()
            .withTelemetryReportingDisabled()
            .onError { throwable: Throwable ->  Log.d("Beacons",throwable.toString()) }
            .build()

        val notification: Notification = NotificationCompat.Builder(this, "")
            .setContentTitle("Skanowanie beaconów")
            .setContentText("Skanowanie beaconów w toku")
            .setSmallIcon(R.drawable.ic_rss_feed_white_24dp)
            .setContentIntent(pendingIntent)
            .build()

        mObservationHandler = proximityObserver.startObserving(BeaconUtils.beaconZones)

        startForeground(1, notification)

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        toast("Destroyed")
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}