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

        questions.findOne()?.sports?.forEach { item ->
            if (!item.received) {
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

    override suspend fun updateSportQuestion(question: String):Boolean {

       return questions.updateOne(
            (Question::sports / Sports::question) eq question,
            setValue(Question::sports.posOp / Sports::received, true)
        ).wasAcknowledged()

    }

    override suspend fun getEntertainmentQuestion(): Entertainment? {
        val availableQuestion = mutableListOf<Entertainment?>()

        questions.findOne()?.entertainment?.forEach { item ->
            if (!item.received) {
                availableQuestion.add(
                    Entertainment(
                        question = item.question,
                        options = item.options,
                        answer = item.answer,
                    )
                )
            }
        }


        return availableQuestion.randomOrNull()






    }

    override suspend fun updateEntertainmentQuestion(question: String): Boolean {

        return questions.updateOne(
            (Question::entertainment / Entertainment::question) eq question,
            setValue(Question::entertainment.posOp / Entertainment::received, true)
        ).wasAcknowledged()

    }

    override suspend fun getAcademicQuestion(): Academic? {
        val availableQuestion = mutableListOf<Academic?>()

        questions.findOne()?.academic?.forEach { item ->
            if (!item.received) {
                availableQuestion.add(
                    Academic(
                        question = item.question,
                        options = item.options,
                        answer = item.answer,
                    )
                )
            }
        }


        return availableQuestion.randomOrNull()



    }

    override suspend fun updateAcademicQuestion(question: String): Boolean {
        return  questions.updateOne(
            (Question::academic / Academic::question) eq question,
            setValue(Question::academic.posOp / Academic::received, true)
        ).wasAcknowledged()

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

        return if (availableQuestion.size < 1){
            FeeSizeResponse(
                size = size,
                entryFee = fee!!,
                success = true
            )
        }else{
            FeeSizeResponse(
                size = size,
                entryFee = fee!!,
                success = false

            )
        }






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

        return if (availableQuestion.size < 1){
            FeeSizeResponse(
                size = size,
                entryFee = fee!!,
                success = true
            )
        }else{
            FeeSizeResponse(
                size = size,
                entryFee = fee!!,
                success = false

            )
        }


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

        return if (availableQuestion.size < 1){
            FeeSizeResponse(
                size = size,
                entryFee = fee!!,
                success = true
            )
        }else{
            FeeSizeResponse(
                size = size,
                entryFee = fee!!,
                success = false

            )
        }


    }


}