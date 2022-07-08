package com.example.route

import com.example.domain.model.EndPoints
import com.example.domain.model.UserSession
import com.example.domain.model.requests.ApiUserRequest
import com.example.domain.model.response.ApiResponse
import com.example.domain.model.user.User
import com.example.domain.model.user.account_info.Account
import com.example.domain.repository.UserDataSource
import com.example.security.hashing.HashingService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Route.signUpRoute(

    hashingService: HashingService,
    userDataSource: UserDataSource,
    app:Application
) {


    post(EndPoints.SignUpUser.path) {


        val request = call.receiveOrNull<ApiUserRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest,"Request is null")
            return@post
        }

        app.log.info(request.username,request.email)


        val areFieldsBlank = request.username.isBlank() || request.password.isBlank()
        val isPwTooShort = request.password.length < 8

        if (areFieldsBlank || isPwTooShort) {
            call.respond(HttpStatusCode.Conflict)
            return@post
        }

        val saltedHash = hashingService.generateSaltedHash(request.password, application = application)


        val user = User(
            phoneNumber = request.phoneNumber,
            username = request.username,
            password = saltedHash.hash,
            email = request.email,
            salt = saltedHash.salt,
            account = Account()

        )

        val wasAcknowledged = userDataSource.saveUserInfo(user)

        if (!wasAcknowledged) {
            call.respond(HttpStatusCode.Conflict,"USER ALREADY EXIST")
        }


        call.sessions.set(UserSession(id = user.id, username =user.username, email = user.email))
        call.respond(
            message = ApiResponse(success = true),
            status = HttpStatusCode.OK
        )



    }


}