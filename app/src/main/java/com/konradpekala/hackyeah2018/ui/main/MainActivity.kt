package com.konradpekala.hackyeah2018.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.konradpekala.hackyeah2018.R
import com.konradpekala.hackyeah2018.data.model.CardInfo
import com.konradpekala.hackyeah2018.ui.addcard.AddCardActivity
import com.konradpekala.hackyeah2018.ui.base.BaseActivity
import com.konradpekala.hackyeah2018.ui.main.MainMvp
import com.konradpekala.hackyeah2018.ui.main.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),MainMvp.View {

    private val mPresenter = MainPresenter(this)
    private lateinit var mListAdapter: CardsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this,AddCardActivity::class.java))

        setSupportActionBar(toolbarMain)
        initList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    private fun initList(){
        mListAdapter = CardsAdapter(ArrayList(), this)
        listCards.adapter = mListAdapter
        listCards.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(listCards)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.title){
            "enablePaying" -> mPresenter.onEnableScanningClick()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun updateCards(cards: List<CardInfo>) {
        mListAdapter.list += cards
        mListAdapter.notifyDataSetChanged()
    }
}
