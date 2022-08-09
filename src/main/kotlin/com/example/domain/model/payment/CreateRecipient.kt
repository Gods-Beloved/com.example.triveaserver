package com.example.domain.model.payment

import java.util.Currency

@kotlinx.serialization.Serializable
data class CreateRecipient(
    val type:String? = "mobile_money",
    val name:String,
    val account_number:String,
    val bank_code:String,
    val currency:String?= "GHS"

)
