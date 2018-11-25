package com.konradpekala.hackyeah2018.data.network

import com.konradpekala.hackyeah2018.data.model.CardInfo
import com.konradpekala.hackyeah2018.data.model.Payment
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET

import retrofit2.http.POST
import retrofit2.http.Path

interface WebApi {

    @POST("/cards/add_card")
    fun saveCard(@Body cardInfo: CardInfo): Single<String>

    @GET("/checkout/{beacon_token}")
    fun getPrice(@Path("beacon_token") beaconToken: String): Single<HashMap<String,Any>>

    @POST("/payment")
    fun payment(@Body payment: Payment): Single<String>
}