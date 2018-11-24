package com.konradpekala.hackyeah2018.ui.main

import com.konradpekala.hackyeah2018.ui.base.MvpPresenter
import com.konradpekala.hackyeah2018.ui.base.MvpView

interface MainMvp{
    interface View: MvpView
    interface Presenter: MvpPresenter{
        fun onEnableScanningClick()
    }
}