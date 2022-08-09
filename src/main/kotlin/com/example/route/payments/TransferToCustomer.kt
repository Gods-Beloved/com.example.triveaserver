package com.example.route.payments

import com.example.domain.model.EndPoints
import com.example.domain.model.payment.CreateRecipient
import com.example.domain.model.payment.Transfer
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Route.transferToCustomer(){

    post(EndPoints.Transfer.path) {

        call.receiveOrNull<Transfer>()


    }

}