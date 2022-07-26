package com.example.data.repository

import com.example.domain.model.questions.Sports
import com.example.domain.model.user.User
import com.example.domain.repository.UserDataSource
import io.ktor.util.*
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineDatabase

class UserDataSourceImpl(
    database:CoroutineDatabase
):UserDataSource {

    private val users = database.getCollection<User>()

    override suspend fun getUserInfo(userId: String): User? {
        return users.findOne(filter = User::id eq userId)
    }

    override suspend fun signInUser(username: String):User? {
        return users.findOne(or(User::username eq username ,User::email eq username ) )
    }


    override suspend fun saveUserInfo(user:User): Boolean {
        val existingUser = users.findOne(filter = User::email eq user.email)
        return if (existingUser == null) {
            users.insertOne(document = user).wasAcknowledged()
        } else {
            true
        }
    }

    override suspend fun saveUserSignUpInfo(user: User): Boolean {
        val existingUser = users.findOne(filter = User::email eq user.email)
        return if (existingUser == null) {
            users.insertOne(document = user).wasAcknowledged()
        } else {
           false
        }
    }



    override suspend fun deleteUser(userId: String): Boolean {
        return users.deleteOne(filter = User::id eq userId).wasAcknowledged()
    }

    override suspend fun updateUserInfo(userId: String,username:String): Boolean {

        return users.updateOne(
            filter = User::id eq userId,
            update = setValue(
                property = User::username,
                value = username
            )
        ).wasAcknowledged()
    }


}