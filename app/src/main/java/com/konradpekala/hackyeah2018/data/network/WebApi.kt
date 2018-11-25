package com.konradpekala.hackyeah2018.data.network

import io.reactivex.Single
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface WebApi {
    @FormUrlEncoded
    @POST("/cards/add_card")
    fun saveCard(@FieldMap map: HashMap<String, String>): Single<String>
}