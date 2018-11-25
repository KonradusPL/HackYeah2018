package com.konradpekala.hackyeah2018.ui.addcard

import android.util.Log
import com.konradpekala.hackyeah2018.data.model.CardInfo
import com.konradpekala.hackyeah2018.data.repository.CardRepository
import com.konradpekala.hackyeah2018.ui.base.BasePresenter


class AddCardPresenter<V: AddCardMvp.View>(view: V, val cardRepo: CardRepository)
    : BasePresenter<V>(view),AddCardMvp.Presenter {

    override fun onSaveCardClick(cardNumber: String, date: String, cvc: String, fullName: String) {
        if (date.length != 5 || cvc.length != 3) {
            view.showMessage("Niepoprawne wartości")
            return
        }
        val month = date.substring(0, 2)
        val year = date.substring(3, 5)

        val cardinfo = CardInfo(cardNumber.toLong(),month.toInt(),year.toInt(),cvc,fullName)

        view.showLoading()
        view.disableButton()

        compositeDisposable.add(cardRepo.saveCard(cardinfo).subscribe({ t: String? ->
            view.showMessage("Udało się !")
            view.hideLoading()
            view.enableButton()
        }, { t: Throwable? ->
            view.showMessage("error: ${t.toString()}")
            view.hideLoading()
            view.enableButton()
            Log.d("saveCard", t.toString())
        }))
    }

    override fun start() {
        super.start()
    }

}