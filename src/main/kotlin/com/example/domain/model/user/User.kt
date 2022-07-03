package com.example.domain.model.user

import com.example.domain.model.user.account_info.Account
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import org.bson.types.ObjectId
import org.litote.kmongo.Data
import org.litote.kmongo.Id
import org.litote.kmongo.newId

@kotlinx.serialization.Serializable
data class User(


    @SerialName("id")
    val id: String = ObjectId().toString(),
    val username:String,
    val salt:String?,
    val profilePicture:String? = null,
    val country:String?= null,
    val phoneNumber:String?= null,
    val bioData:String? = null,
    val password:String?,
    val account: Account,
    val email:String,
)
