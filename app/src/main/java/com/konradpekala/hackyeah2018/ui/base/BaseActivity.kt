package com.konradpekala.hackyeah2018.ui.base

import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.toast

open class BaseActivity : AppCompatActivity(), MvpView {

    override fun hideKeyboard() {

    }

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun showMessage(message: Int) {
        toast(message)
    }

    override fun isConnectedToNetwork(): Boolean {
        return true
    }

    override fun getCtx(): Context {
        return this
    }

    override fun getActivity(): Activity {
        return this
    }
}