package com.example.route.transactions

import io.ktor.server.routing.*

fun Route.verifyPayment(){
    get("api.paystack.co/transaction/verify/:{reference}") {

    }
}