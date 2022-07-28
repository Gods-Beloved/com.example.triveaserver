package com.example.domain.model.questions

@kotlinx.serialization.Serializable
data class Entry(
    val sportsPrice:Int,
    val academicPrice:Int,
    val entertainmentPrice:Int,
)
