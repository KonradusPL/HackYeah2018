package com.konradpekala.hackyeah2018.ui.payment

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import com.konradpekala.hackyeah2018.R
import com.konradpekala.hackyeah2018.data.model.CardInfo
import com.konradpekala.hackyeah2018.data.model.Payment
import com.konradpekala.hackyeah2018.data.network.ServerNetworking
import com.konradpekala.hackyeah2018.data.repository.CardRepository
import com.konradpekala.hackyeah2018.ui.base.BaseActivity
import com.konradpekala.hackyeah2018.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : BaseActivity() {

    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val webApi = ServerNetworking.webApi

        val cards = CardRepository.cardsCatche
        if(cards.size == 0)
            showMessage("You don't have a credit card")
        else{
            val cardNumber = cards[0].cardNumber.toString()
            val beaconToken = CardRepository.currentBeacon
            val price = CardRepository.currentPrice

            Log.d("payment",cardNumber)
            Log.d("payment",beaconToken)
            Log.d("payment",price)

            compositeDisposable.add(webApi.payment(Payment(price,cardNumber,beaconToken))
                .subscribeOn(SchedulerProvider.io())
                .observeOn(SchedulerProvider.ui())
                .subscribe({t: String? ->
                    progressBar.visibility = View.GONE
                    textView.text = "Payment accepted!"
                    imageView.visibility = View.VISIBLE
                    showDialog()
                },{t: Throwable? ->
                    progressBar.visibility = View.GONE
                }))
        }

    }
    fun showDialog(){
        val dialog = AlertDialog.Builder(this)
            .setIcon(R.drawable.ic_people_black_24dp)
            .setTitle("Hey there!")
            .setMessage("First time you visited that place! Hope to see you more!")
            .setPositiveButton("OK",{dialogInterface, i -> })
            .create()
            .show()
    }
}
