package com.konradpekala.hackyeah2018.ui.addcard

import com.konradpekala.hackyeah2018.ui.base.MvpPresenter
import com.konradpekala.hackyeah2018.ui.base.MvpView

interface AddCardMvp {
    interface View: MvpView{
        fun enableButton()
        fun disableButton()
        fun showLoading()
        fun hideLoading()
    }
    interface Presenter: MvpPresenter{
        fun onSaveCardClick(cardNumber: String, date: String, cvc: String, fullName: String)
    }
}