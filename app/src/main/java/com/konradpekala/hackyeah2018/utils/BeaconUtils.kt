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
    private val zonesString = arrayListOf("Kanciapa","Wyjście poziom -1","Wejście główne",
        "Chill zone","Pokój organizatora","Aula spadochronowa","Wyjście poziom 0","Jajko",
        "Foto","Ksero","Sala 105","Aula B")

    private var lastBeaconDate: Date? = null

    init {
        for(zone in zonesString){
            beaconZones.add(
                ProximityZoneBuilder()
                .forTag(zone)
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
}