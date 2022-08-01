package com.example.plugins

import com.example.domain.repository.QuestionsDataSource
import com.example.domain.repository.UserDataSource
import com.example.route.*
import com.example.route.questions.*
import com.example.routes.deleteUserRoute
import com.example.routes.signOutRoute
import com.example.security.hashing.HashingService
import com.example.security.token.TokenConfig
import com.example.security.token.TokenService
import io.ktor.server.routing.*
import io.ktor.server.application.*
import org.koin.java.KoinJavaComponent.inject

fun Application.configureRouting(
    hashingService:HashingService,
    tokenService: TokenService,
    tokenConfig: TokenConfig

) {

    routing {

        val userDataSource:UserDataSource by inject(UserDataSource::class.java)
        val questionsDataSource:QuestionsDataSource by inject(QuestionsDataSource::class.java)


        rootRoute()

        signInRoute(userDataSource,hashingService,tokenService,tokenConfig,application)

        signUpRoute(userDataSource = userDataSource, hashingService = hashingService, app = application)

        signOutRoute()

        authenticate()

        getSecreteInfo()

//Questions Routes
        getSportsQuestions(questionsDataSource = questionsDataSource)

        getSportPrice(questionsDataSource = questionsDataSource)

        getEntertainmentQuestions(questionsDataSource = questionsDataSource)

        getEntertainmentPrice(questionsDataSource = questionsDataSource)

        getAcademicQuestions(questionsDataSource = questionsDataSource)

        getAcademicPrice(questionsDataSource = questionsDataSource)

        deleteUserRoute(application,userDataSource)

        getUserInfoRoute(app = application, userDataSource = userDataSource)

        tokenVerificationRoute(application,userDataSource)

        updateUserRoute(application, userDataSource = userDataSource)

        authorizedRoute()

        unauthorizedRoute()



    }

}
