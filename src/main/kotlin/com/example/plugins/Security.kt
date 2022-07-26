//package com.example.plugins
//
//import com.auth0.jwt.JWT
//import com.auth0.jwt.algorithms.Algorithm
//import com.example.domain.model.EndPoints
//import com.example.security.token.TokenConfig
//import io.ktor.server.application.*
//import io.ktor.server.auth.*
//import io.ktor.server.auth.jwt.*
//import io.ktor.server.response.*
//
//
//fun Application.configureSecurity(config: TokenConfig) {
//    authentication {
//        jwt {
//            realm = this@configureSecurity.environment.config.property("jwt.realm").getString()
//            verifier(
//                JWT
//                    .require(Algorithm.HMAC256(config.secret))
//                    .withAudience(config.audience)
//                    .withIssuer(config.issuer)
//                    .build()
//            )
//            validate { credential ->
//                if (credential.payload.audience.contains(config.audience)) {
//                    JWTPrincipal(credential.payload)
//                } else null
//            }
//
//            challenge { _, _ ->
//                call.respondRedirect(EndPoints.Unauthorized.path)
//            }
//        }
//    }
//
//}