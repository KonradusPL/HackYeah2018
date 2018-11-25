package com.konradpekala.hackyeah2018.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.konradpekala.hackyeah2018.R
import com.konradpekala.hackyeah2018.data.model.CardInfo
import kotlinx.android.synthetic.main.item_card.view.*

class CardsAdapter(val list: ArrayList<CardInfo>, val context: Context): RecyclerView.Adapter<CardsAdapter.CardViewHolder>() {
    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view =  LayoutInflater.from(context)
            .inflate(R.layout.item_card,parent,false)
        return CardViewHolder(view)
    }

    class CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(cardInfo: CardInfo){
            var month = cardInfo.expirationMonth.toString()
            var year = cardInfo.expirationYear.toString()
            if(month.length==1)
                month = "0$month"
            if(year.length==1)
                year = "0$year"

            itemView.layoutCard.apply {
                cardNumber = cardInfo.cardNumber.toString()
                cardHolderName = cardInfo.fullName
                setCardExpiry("$month/$year")
                cvv = cardInfo.cvc
            }
        }
    }
}