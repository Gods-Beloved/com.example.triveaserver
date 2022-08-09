package com.example.domain.model.payment

@kotlinx.serialization.Serializable
data class Transfer(
    val source:String,
    val amount:Int,
    val recipient:String,
    val reason:String
)
