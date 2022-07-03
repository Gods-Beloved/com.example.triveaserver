package com.example.route

import com.example.domain.model.EndPoints
import com.example.domain.model.requests.ApiSignInRequest
import com.example.domain.model.response.ApiResponse
import com.example.domain.repository.UserDataSource
import com.example.security.hashing.HashingService
import com.example.security.hashing.SaltedHash
import com.example.security.token.TokenClaim
import com.example.security.token.TokenConfig
import com.example.security.token.TokenService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.signInRoute(
userDataSource: UserDataSource,
hashingService: HashingService,
tokenService: TokenService,
tokenConfig: TokenConfig,
app:Application
){

    post(EndPoints.SignInUser.path) {

        app.log.info("Inside Post Pipeline")
        val request = call.receiveOrNull<ApiSignInRequest>() ?:kotlin.run {
            call.respond(HttpStatusCode.BadRequest,)
            return@post
        }


        val user = userDataSource.signInUser(request.username)


        if(user == null){
            call.respond(HttpStatusCode.Conflict,"Invalid User")
            return@post
        }


        val isValidPassword = hashingService.verify(
            value = request.password,
            saltedHash = SaltedHash(
                hash = user.password.toString(),
                salt = user.salt.toString()
            ),
            app=application
        )

        if (!isValidPassword){
            app.log.info("ERROR SAVING THE USER")
            call.respondRedirect(EndPoints.Unauthorized.path)
            return@post
        }

        val token = tokenService.generate(
            config = tokenConfig,
            TokenClaim(
                name = "userId",
                value= user.id.toString()
            )
        )

        call.respond(
            status = HttpStatusCode.OK,
            message = ApiResponse(
               success = true,
                token = token
            )
        )



    }

}