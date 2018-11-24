package com.konradpekala.hackyeah2018.ui.base

import io.reactivex.disposables.CompositeDisposable

open class BasePresenter(protected val view: MvpView): MvpPresenter {

    protected val compositeDisposable = CompositeDisposable()

    override fun start() {
    }

    override fun stop() {
        compositeDisposable.clear()
    }
}