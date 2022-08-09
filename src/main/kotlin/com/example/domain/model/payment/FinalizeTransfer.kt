package com.example.domain.model.payment

@kotlinx.serialization.Serializable
data class FinalizeTransfer(
    val transfer_code:String,
    val otp:String

)
