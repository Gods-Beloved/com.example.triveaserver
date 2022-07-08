package com.example.route

import com.example.domain.model.*
import com.example.domain.model.requests.ApiTokenRequest
import com.example.domain.model.user.User
import com.example.domain.model.user.account_info.Account
import com.example.domain.repository.UserDataSource
import com.example.util.Constants.AUDIENCE
import com.example.util.Constants.ISSUER
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.gson.GsonFactory

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.util.pipeline.*

fun Route.tokenVerificationRoute(
    app: Application,
    userDataSource: UserDataSource
) {
    post(EndPoints.TokenVerification.path) {
        val request = call.receive<ApiTokenRequest>()
        val userSession = call.principal<UserSession>()
      //  val principal = call.principal<JWTPrincipal>()
      //  val userId = principal?.getClaim("userId", String::class)

        if (request.tokenId.isNotEmpty()) {

           val tokenSuccess = verifyGoogleTokenId(tokenId = request.tokenId,app)

            if (tokenSuccess != null) {

                if (userSession != null){
                    if(tokenSuccess.payload.email == userSession.email){
                        call.respondRedirect(EndPoints.Authorized.path)

                    }else{
                       call.respond(HttpStatusCode.BadRequest,"Google ID Token Error")
                    }
                }else{
                    saveUserToDatabase(
                        app = app,
                        result = tokenSuccess,
                        userDataSource = userDataSource
                    )
                }


            } else {
                app.log.info("TOKEN VERIFICATION FAILED")
                call.respondRedirect(EndPoints.Unauthorized.path)
            }
        } else {
            app.log.info("EMPTY TOKEN ID")
            call.respondRedirect(EndPoints.Unauthorized.path)
        }
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.saveUserToDatabase(
    app: Application,
    result: GoogleIdToken,
    userDataSource: UserDataSource
) {

    val name = result.payload["name"].toString()
    val emailAddress = result.payload["email"].toString()
    val profilePhoto = result.payload["picture"].toString()
    val user = User(
        profilePicture= profilePhoto,
        username = name,
        email = emailAddress,
        password = null,
        salt = null,
        account = Account()
    )

    val response = userDataSource.saveUserInfo(user = user)
    if (response) {
        app.log.info("USER SUCCESSFULLY SAVED/RETRIEVED")
        call.sessions.set(UserSession(id = user.id, username = name, email = emailAddress))
        call.respondRedirect(EndPoints.Authorized.path)
    } else {
        app.log.info("ERROR SAVING THE USER")
        call.respondRedirect(EndPoints.Unauthorized.path)
    }
}

fun verifyGoogleTokenId(tokenId: String,app:Application): GoogleIdToken? {

val gson = GsonFactory()

    return try{
    val verifier = GoogleIdTokenVerifier.Builder(NetHttpTransport(),gson)
        .setAudience(listOf(AUDIENCE))
        .setIssuer(ISSUER)
        .build()

    verifier.verify(tokenId)
    }catch (e:Exception){
        app.log.info("exception $e")
        null
    }



}