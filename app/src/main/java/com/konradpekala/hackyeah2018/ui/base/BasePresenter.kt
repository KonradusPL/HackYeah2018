package com.konradpekala.hackyeah2018.ui.base

import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<V: MvpView>(protected val view: V): MvpPresenter {

    protected val compositeDisposable = CompositeDisposable()

    override fun start() {
    }

    override fun stop() {
        compositeDisposable.clear()
    }
}