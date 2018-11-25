package com.konradpekala.hackyeah2018.ui.main

import com.konradpekala.hackyeah2018.data.model.CardInfo
import com.konradpekala.hackyeah2018.ui.base.MvpPresenter
import com.konradpekala.hackyeah2018.ui.base.MvpView

interface MainMvp{
    interface View: MvpView{
        fun updateCards(cards: List<CardInfo>)

    }
    interface Presenter: MvpPresenter{
        fun onEnableScanningClick()
    }
}