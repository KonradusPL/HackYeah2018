package com.konradpekala.hackyeah2018.data.repository

import com.konradpekala.hackyeah2018.data.model.CardInfo
import com.konradpekala.hackyeah2018.data.network.ServerNetworking
import com.konradpekala.hackyeah2018.data.network.WebApi
import com.konradpekala.hackyeah2018.utils.SchedulerProvider
import io.reactivex.Single

object CardRepository {

    private val cardsCatche = ArrayList<CardInfo>()

    val webApi = ServerNetworking.webApi

    fun saveCard(cardInfo: CardInfo): Single<String>{
        return webApi.saveCard(cardInfo)
            .subscribeOn(SchedulerProvider.io())
            .observeOn(SchedulerProvider.ui())
            .doOnSuccess {
                cardsCatche.add(cardInfo)
            }
    }

    fun getCards(): Single<ArrayList<CardInfo>>{
        return Single.just(cardsCatche)
    }
}