package com.example.route.questions

import com.example.domain.model.EndPoints
import com.example.domain.model.questions.QuestionResponse
import com.example.domain.repository.QuestionsDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getSportsQuestions(
    questionsDataSource: QuestionsDataSource
){

        get(EndPoints.GetSportsQuestions.path) {


    val questions = questionsDataSource.getSportQuestion()

            if (questions == null){
                call.respond(HttpStatusCode.OK, message =
                QuestionResponse(
                    success = false

                )
                )
            }

    call.respond(HttpStatusCode.OK, message =
    QuestionResponse(
        success = true,
        sports =  questions
    )
    )





        }


}