package com.konradpekala.hackyeah2018.utils

import android.util.Log
import com.estimote.proximity_sdk.api.ProximityZone
import com.estimote.proximity_sdk.api.ProximityZoneBuilder
import java.util.*

object BeaconUtils {
    interface BeaconListener{
        fun onEnterZone(tag: String)
    }
    private val defaultRange = 1.0
    val beaconZones = ArrayList<ProximityZone>()
    var listener: BeaconListener? = null
    private val zone = "hackyeah"

    private var lastBeaconDate: Date? = null

    init {
        beaconZones.add(
            ProximityZoneBuilder().forTag(zone)
                .inCustomRange(5.0)
                .onEnter{
                    Log.d("Beacons","Enter")
                    /*if(lastBeaconDate == null || Date().time - lastBeaconDate!!.time < 5000){
                        lastBeaconDate = Date()
                        listener?.onEnterZone(zone)
                    }*/
                    listener?.onEnterZone(zone)
                }
                .build())

    }
}