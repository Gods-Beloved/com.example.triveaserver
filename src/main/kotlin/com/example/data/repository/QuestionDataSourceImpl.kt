package com.example.data.repository

import com.example.domain.model.questions.*
import com.example.domain.repository.QuestionsDataSource
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.setValue

class QuestionDataSourceImpl(
    database: CoroutineDatabase

):QuestionsDataSource {

    private val questions = database.getCollection<Question>()


    override suspend fun getSportQuestion(): Sports? {
        val availableQuestion = mutableListOf<Sports?>()

        questions.findOne()?.live?.sports?.forEach {
            item ->
            if (!item.received){
                availableQuestion.add(
                    Sports(
                        question = item.question,
                        options = item.options,
                        answer = item.answer,
                    )
                )
            }
        }



        return availableQuestion.randomOrNull()

       // return questions.findOne()?.live?.get(0)?.sports?.toList()
    }

    override suspend fun getEntertainmentQuestion(): Entertainment? {
        val availableQuestion = mutableListOf<Entertainment?>()

        questions.findOne()?.live?.entertainment?.forEach {
                item ->
            if (!item.received){
                availableQuestion.add(
                    Entertainment(
                        question = item.question,
                        options = item.options,
                        answer = item.answer,
                    )
                )
            }
        }

        val currentQuestion =  availableQuestion.randomOrNull()

//        questions.updateOne(
//            filter = Question::live. eq "What movie does NOT have Brad Pitt in it?",
//            update = setValue(
//                property = Entertainment::received,
//                value = true
//
//            )
//        )

        return currentQuestion
    }

    override suspend fun getSportPrice(): FeeSizeResponse {

        val fee = questions.findOne()?.live?.entry?.sportsprice

        val availableQuestion = mutableListOf<Sports?>()

        questions.findOne()?.live?.sports?.forEach {
                item ->
            if (!item.received){
                availableQuestion.add(
                    Sports(
                        question = item.question,
                        options = item.options,
                        answer = item.answer,
                    )
                )
            }
        }

        val size = availableQuestion.size

        return FeeSizeResponse(
            size = size,
            entryfee = fee!!
        )




    }

    override suspend fun getEntertainmentPrice(): FeeSizeResponse {

        val fee = questions.findOne()?.live?.entry?.entertainmentprice

        val availableQuestion = mutableListOf<Entertainment?>()

        questions.findOne()?.live?.entertainment?.forEach {
                item ->
            if (!item.received){
                availableQuestion.add(
                    Entertainment(
                        question = item.question,
                        options = item.options,
                        answer = item.answer,
                    )
                )
            }
        }

        val size = availableQuestion.size

        return FeeSizeResponse(
            size = size,
            entryfee = fee!!
        )




    }


}