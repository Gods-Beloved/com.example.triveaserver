package com.example.route.payments

import com.example.domain.model.EndPoints
import com.example.domain.model.payment.FinalizeTransfer
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Route.finishTransferToCustomer(){
    post(EndPoints.FinishTransfer.path) {

        call.receiveOrNull<FinalizeTransfer>()
    }
}