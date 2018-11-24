package com.konradpekala.hackyeah2018.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.konradpekala.hackyeah2018.R
import com.konradpekala.hackyeah2018.ui.addcard.AddCardActivity
import com.konradpekala.hackyeah2018.ui.base.BaseActivity
import com.konradpekala.hackyeah2018.ui.main.MainMvp
import com.konradpekala.hackyeah2018.ui.main.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),MainMvp.View {

    private val mPresenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this,AddCardActivity::class.java))

        setSupportActionBar(toolbarMain)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.title){
            "enablePaying" -> mPresenter.onEnableScanningClick()
        }

        return super.onOptionsItemSelected(item)
    }

}
