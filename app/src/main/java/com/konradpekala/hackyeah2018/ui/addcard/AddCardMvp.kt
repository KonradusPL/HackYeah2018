package com.konradpekala.hackyeah2018.ui.addcard

import com.konradpekala.hackyeah2018.data.model.CardInfo
import com.konradpekala.hackyeah2018.ui.base.MvpPresenter
import com.konradpekala.hackyeah2018.ui.base.MvpView

interface AddCardMvp {
    interface View: MvpView{

    }
    interface Presenter: MvpPresenter{
        fun onSaveCardClick(cardInfo: CardInfo)
    }
}