package com.konradpekala.hackyeah2018.data.repository

import com.konradpekala.hackyeah2018.data.network.WebApi
import com.konradpekala.hackyeah2018.utils.SchedulerProvider
import io.reactivex.Single

class CardRepository(val webApi: WebApi) {

    fun saveCard(map: HashMap<String,String>): Single<String>{
        return webApi.saveCard(map)
            .subscribeOn(SchedulerProvider.io())
            .observeOn(SchedulerProvider.ui())
    }
}