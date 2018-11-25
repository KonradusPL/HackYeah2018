package com.konradpekala.hackyeah2018.ui.main

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.get
import com.konradpekala.hackyeah2018.R
import com.konradpekala.hackyeah2018.data.AppCache
import com.konradpekala.hackyeah2018.data.model.CardInfo
import com.konradpekala.hackyeah2018.data.repository.CardRepository
import com.konradpekala.hackyeah2018.ui.addcard.AddCardActivity
import com.konradpekala.hackyeah2018.ui.base.BaseActivity
import com.konradpekala.hackyeah2018.ui.main.MainMvp
import com.konradpekala.hackyeah2018.ui.main.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable



class MainActivity : BaseActivity(),MainMvp.View {

    private val mPresenter = MainPresenter(this,CardRepository)
    private lateinit var mListAdapter: CardsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbarMain)

        initUI()
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        mPresenter.onCreate()
    }

    override fun onStop() {
        super.onStop()
        mPresenter.stop()
    }

    override fun onStart() {
        super.onStart()
        mPresenter.start()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        val drawable = menu[0].icon
        drawable.mutate()
        if(drawable != null){
            if (AppCache.isBeaconServiceRunning){
                drawable.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)

            }
            else{
                drawable.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP)
            }
        }


        return true
    }

    private fun initUI(){
        fabAddCard.setOnClickListener {
            startActivity(Intent(this@MainActivity,AddCardActivity::class.java))
        }
        initList()
    }

    private fun initList(){
        mListAdapter = CardsAdapter(ArrayList(), this)
        listCards.adapter = mListAdapter
        listCards.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.title){
            "enablePaying" -> {
                val drawable = item.icon
                if (AppCache.isBeaconServiceRunning){
                    if (drawable != null) {
                        drawable.mutate()
                        drawable.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP)
                    }
                }
                else{
                    if (drawable != null) {
                        drawable.mutate()
                        drawable.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
                    }
                }
                mPresenter.onEnableScanningClick()
            }
        }

        return super.onOptionsItemSelected(item)
    }


    override fun updateCards(cards: List<CardInfo>) {
        mListAdapter.list.clear()
        mListAdapter.list += cards
        mListAdapter.notifyDataSetChanged()
    }
}
