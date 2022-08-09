package com.example.domain.model.payment

@kotlinx.serialization.Serializable
data class MobileMoney(
    val phone:String,
    val provider:String
)
