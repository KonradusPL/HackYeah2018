package com.konradpekala.hackyeah2018.data.model

data class CardInfo(val cardNumber: Long,
                    val expirationYear: Int,
                    val expirationMonth: Int,
                    val cvc: String,
                    val fullName: String){

    override fun toString(): String {
        return "$cardNumber $expirationYear $expirationMonth $cvc $fullName"
    }

}