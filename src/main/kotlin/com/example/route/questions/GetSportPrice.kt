package com.example.route.questions

import com.example.domain.model.EndPoints
import com.example.domain.model.questions.FeeSizeResponse
import com.example.domain.model.questions.QuestionResponse
import com.example.domain.repository.QuestionsDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getSportPrice(
    questionsDataSource: QuestionsDataSource
){

        get(EndPoints.GetSportPrice.path) {


    val enterQuestions = questionsDataSource.getSportPrice()

            val fee = enterQuestions?.entryfee!!
            val size = enterQuestions?.size!!


            call.respond(HttpStatusCode.OK, message =
            FeeSizeResponse(
                entryfee = fee,
                size = size
            )
            )


        }


}