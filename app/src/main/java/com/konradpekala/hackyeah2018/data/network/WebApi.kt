package com.konradpekala.hackyeah2018.data.network

import com.konradpekala.hackyeah2018.data.model.CardInfo
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface WebApi {

    @POST("/cards/add_card")
    fun saveCard(@Body cardInfo: CardInfo): Single<String>
}