package com.example.data.repository

import com.example.domain.model.questions.*
import com.example.domain.repository.QuestionsDataSource
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineDatabase

class QuestionDataSourceImpl(
    database: CoroutineDatabase

):QuestionsDataSource {

    private val questions = database.getCollection<Question>()


    override suspend fun getSportQuestion(): Sports? {
        val availableQuestion = mutableListOf<Sports?>()

        questions.findOne()?.sports?.forEach {
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

        val currentQuestion =  availableQuestion.randomOrNull()

        val updateSuccess =  questions.updateOne(
            (Question::sports / Sports::question) eq currentQuestion?.question.toString(),
            setValue(Question::sports.posOp / Sports::received, true)
        ).wasAcknowledged()

        return if (updateSuccess){
            currentQuestion
        } else{
            null
        }





       // return questions.findOne()?.live?.get(0)?.sports?.toList()
    }

    override suspend fun getEntertainmentQuestion(): Entertainment? {
        val availableQuestion = mutableListOf<Entertainment?>()

        questions.findOne()?.entertainment?.forEach {
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

      val updateSuccess =  questions.updateOne(
            (Question::entertainment / Entertainment::question) eq currentQuestion?.question.toString(),
            setValue(Question::entertainment.posOp / Entertainment::received, true)
        ).wasAcknowledged()

        return if (updateSuccess){
            currentQuestion
        } else{
            null
        }



    }

    override suspend fun getAcademicQuestion(): Academic? {
        val availableQuestion = mutableListOf<Academic?>()

        questions.findOne()?.academic?.forEach {
                item ->
            if (!item.received){
                availableQuestion.add(
                    Academic(
                        question = item.question,
                        options = item.options,
                        answer = item.answer,
                    )
                )
            }
        }

        val currentQuestion =  availableQuestion.randomOrNull()

        val updateSuccess =  questions.updateOne(
            (Question::academic / Academic::question) eq currentQuestion?.question.toString(),
            setValue(Question::academic.posOp / Academic::received, true)
        ).wasAcknowledged()

        return if (updateSuccess){
            currentQuestion
        } else{
            null
        }


    }

    override suspend fun getSportPrice(): FeeSizeResponse {

        val fee = questions.findOne()?.entry?.sportsPrice

        val availableQuestion = mutableListOf<Sports?>()

        questions.findOne()?.sports?.forEach {
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

        val fee = questions.findOne()?.entry?.entertainmentPrice

        val availableQuestion = mutableListOf<Entertainment?>()

        questions.findOne()?.entertainment?.forEach {
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

    override suspend fun getAcademicPrice(): FeeSizeResponse? {
        val fee = questions.findOne()?.entry?.academicPrice

        val availableQuestion = mutableListOf<Academic?>()

        questions.findOne()?.academic?.forEach {
                item ->
            if (!item.received){
                availableQuestion.add(
                    Academic(
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