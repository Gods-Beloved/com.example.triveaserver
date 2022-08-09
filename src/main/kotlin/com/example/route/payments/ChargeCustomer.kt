package com.example.route.payments

import com.example.domain.model.EndPoints
import com.example.domain.model.payment.ChargeCustomer
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.chargeCustomer(){

    post(EndPoints.ChargeCustomer.path) {

        call.receiveOrNull<ChargeCustomer>()


    }

}