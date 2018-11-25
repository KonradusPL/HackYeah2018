package com.konradpekala.hackyeah2018.ui.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.konradpekala.hackyeah2018.data.model.CardInfo

class CardsAdapter(val list: ArrayList<CardInfo>): RecyclerView.Adapter<CardsAdapter.CardViewHolder>() {
    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        TODO()
    }

    class CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}