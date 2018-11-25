package com.konradpekala.hackyeah2018.ui.addcard

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.konradpekala.hackyeah2018.R
import com.konradpekala.hackyeah2018.data.repository.CardRepository
import com.konradpekala.hackyeah2018.ui.base.BaseActivity
import com.konradpekala.hackyeah2018.ui.main.CardsAdapter
import com.konradpekala.hackyeah2018.utils.ExpirationDateWatcher
import com.konradpekala.hackyeah2018.utils.FourDigitsWatcher
import kotlinx.android.synthetic.main.activity_add_card.*


class AddCardActivity: BaseActivity(),AddCardMvp.View {

    private val mPresenter = AddCardPresenter(this, CardRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)
        initUI()

        buttonSaveCard.setOnClickListener {
            val cardNumber = inputCardNumber.text.toString().replace("-","")
            val date = inputExpiredDate.text.toString()
            val cvc = inputCvc.text.toString()
            val fullName = inputFullName.text.toString()
            mPresenter.onSaveCardClick(cardNumber, date, cvc, fullName)
        }

        mPresenter.onCreate()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
        mPresenter.stop()
    }

    private fun initUI() {
        setSupportActionBar(toolbarAddCard)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        inputCardNumber.addTextChangedListener(FourDigitsWatcher())
        inputExpiredDate.addTextChangedListener(ExpirationDateWatcher())
    }

    override fun enableButton() {
        buttonSaveCard.alpha = 1.0f
        buttonSaveCard.isEnabled = true
    }

    override fun disableButton() {
        buttonSaveCard.isEnabled = false
        buttonSaveCard.alpha = 0.5f
    }

    override fun showLoading() {
        progressAddCard.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressAddCard.visibility = View.GONE
    }

}
