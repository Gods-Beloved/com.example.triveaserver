package com.example.domain.repository

import com.example.domain.model.user.User
import org.litote.kmongo.Id

interface UserDataSource {
   // suspend fun getUserInfo(username:String): User?
   // suspend fun saveUserInfo(user: User):Boolean
   // suspend fun deleteUser(userId:String):Boolean
//    suspend fun updateUserInfo(
//        userId: String,
//        firstName:String,
//        lastName:String
//    ):Boolean

       suspend fun getUserInfo(userId:String): User?

       suspend fun signInUser(username:String):User?
       suspend fun saveUserInfo(user: User): Boolean
       suspend fun deleteUser(userId: String): Boolean
       suspend fun updateUserInfo(
           userId: String,
           username:String
       ): Boolean

}