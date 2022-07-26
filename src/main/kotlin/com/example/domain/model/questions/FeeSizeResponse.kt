package com.example.domain.model.questions

@kotlinx.serialization.Serializable
data class FeeSizeResponse(
    val entryfee:Int,
    val size:Int
)
