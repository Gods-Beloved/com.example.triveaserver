package com.example.domain.model.requests

@kotlinx.serialization.Serializable
data class ApiUserRequest(
    val username:String,
    val password:String,
    val email:String,
    val phoneNumber:String,
)