package com.example.domain.repository

import com.example.domain.model.questions.*
import org.litote.kmongo.coroutine.CoroutineDistinctPublisher

interface QuestionsDataSource {
    suspend fun getSportQuestion(): Sports?
    suspend fun getEntertainmentQuestion(): Entertainment?
    suspend fun getAcademicQuestion(): Academic?
    suspend fun getSportPrice():FeeSizeResponse?
    suspend fun getEntertainmentPrice():FeeSizeResponse?
    suspend fun getAcademicPrice():FeeSizeResponse?
    //suspend fun updateReceivedQuestion():Boolean

}