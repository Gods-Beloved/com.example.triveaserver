package com.example.domain.model.payment

@kotlinx.serialization.Serializable
data class ChargeCustomer(
    val amount:Int,
    val email:String,
    val currency:String,
    val mobile_money:MobileMoney,
)
