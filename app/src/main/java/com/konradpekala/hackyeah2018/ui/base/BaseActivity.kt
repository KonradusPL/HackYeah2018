package com.konradpekala.hackyeah2018.ui.base

import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.Gravity
import es.dmoral.toasty.Toasty
import org.jetbrains.anko.toast

open class BaseActivity : AppCompatActivity(), MvpView {

    override fun hideKeyboard() {

    }

    override fun showMessage(message: String) {
        val t = Toasty.info(this,message)
        t.setGravity(Gravity.BOTTOM xor Gravity.CENTER_HORIZONTAL,0,200)
        t.show()
    }

    override fun showMessage(message: Int) {
        ///Toasty.info(this,TextUtils.t).show()
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