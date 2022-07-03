package com.example.domain.model.response

import com.example.domain.model.user.User
import kotlinx.serialization.Contextual

@kotlinx.serialization.Serializable
data class ApiResponse(
    val success:Boolean,
    val user: User? = null,
    val message:String? = null,
    val token:String? = null
)
