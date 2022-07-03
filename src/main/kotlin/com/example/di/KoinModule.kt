package com.example.di

import com.example.data.repository.UserDataSourceImpl
import com.example.domain.repository.UserDataSource
import com.example.util.Constants.DATABASE_NAME
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val koinModule = module {

    val mongoPW = System.getenv("MONGO_PW")

    single{
        KMongo.createClient(
            connectionString = "mongodb+srv://Godwill:$mongoPW@cluster0.fyx16uq.mongodb.net/trivious_database?retryWrites=true&w=majority"
        )
            .coroutine
            .getDatabase(DATABASE_NAME)
    }

    single<UserDataSource> {
        UserDataSourceImpl(get())
    }


}