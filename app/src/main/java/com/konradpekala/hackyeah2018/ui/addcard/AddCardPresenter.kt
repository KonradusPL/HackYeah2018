package com.konradpekala.hackyeah2018.ui.addcard

import com.konradpekala.hackyeah2018.data.model.CardInfo
import com.konradpekala.hackyeah2018.ui.base.BasePresenter
import com.konradpekala.hackyeah2018.ui.base.MvpPresenter

class AddCardPresenter(view: AddCardMvp.View): BasePresenter(view),AddCardMvp.Presenter {
    override fun onSaveCardClick(cardInfo: CardInfo) {

    }
}