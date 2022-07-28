package com.example.domain.model.questions

@kotlinx.serialization.Serializable
data class Question(
    val entry:Entry,
    val entertainment: List<Entertainment>,
    val academic: List<Academic>,
    val sports:List<Sports>
    )
