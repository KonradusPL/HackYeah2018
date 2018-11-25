package com.konradpekala.hackyeah2018.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationManagerCompat
import android.util.Log
import com.konradpekala.hackyeah2018.ui.payment.PaymentActivity

class MyBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("MyBroadcastReceiver","onReceive")
        val action = intent?.action ?: ""
        if(intent != null && ( action == "yes" || action == "no")){
            val notifId = intent.extras["notifId"]

            if(notifId != null)
                NotificationManagerCompat.from(context).cancel(notifId as Int)

            context.startActivity(Intent(context,PaymentActivity::class.java))
        }
    }
}