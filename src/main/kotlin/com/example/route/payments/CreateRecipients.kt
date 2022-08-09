package com.example.route.transactions

import com.example.domain.model.EndPoints
import com.example.domain.model.payment.ChargeCustomer
import com.example.domain.model.payment.CreateRecipient
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Route.createRecipients() {

    post(EndPoints.CreateRecipients.path){

        call.receiveOrNull<CreateRecipient>()

    }

}