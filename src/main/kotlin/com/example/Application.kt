package com.example

import io.ktor.server.application.*
import com.example.plugins.*
import com.example.security.hashing.SHA256HashingServiceImpl
import com.example.security.token.JwtTokenService
import com.example.security.token.TokenConfig

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {


    val tokenService = JwtTokenService()
    val tokenConfig = TokenConfig(
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        expiresIn = 365L * 1000L * 60L * 60L * 24L,
       secret= System.getenv("JWT_SECRET")
    )

    val hashingService = SHA256HashingServiceImpl()


    configureKoin()
    configureAuth()
    configureSecurity(tokenConfig)
    configureRouting(hashingService = hashingService,tokenService=tokenService, tokenConfig = tokenConfig)
    configureSerialization()
    configureMonitoring()

    configureSession()

}
