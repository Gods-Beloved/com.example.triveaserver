package com.example.domain.model

sealed class EndPoints (val path:String){
    object Root:EndPoints(path = "/")
    object TokenVerification:EndPoints(path = "/token_verification")
    object CreateRecipients:EndPoints(path = "/api.paystack.co/transferrecipient")
    object Transfer:EndPoints(path = "/api.paystack.co/transfer")

    object FinishTransfer:EndPoints(path = "/api.paystack.co/transfer/finalize_transfer")
    object GetUserInfo:EndPoints(path = "/get_user" )
    object GetSportsQuestions:EndPoints(path = "/get_sports_questions" )
    object GetEntertainmentQuestions:EndPoints(path = "/get_entertainment_questions" )
    object GetAcademicQuestions:EndPoints(path = "/get_academic_questions" )
    object GetSportPrice:EndPoints(path = "/get_price_sport" )
    object GetEntertainmentPrice:EndPoints(path = "/get_price_entertainment" )
    object GetAcademicPrice:EndPoints(path = "/get_price_academic" )

    object ChargeCustomer:EndPoints(path = "/api.paystack.co/charge")

    object GetTransactions:EndPoints(path = "/api.paystack.co/transaction")

    object SignUpUser:EndPoints(path = "/signup_user")
    object SignInUser:EndPoints(path = "/signin_user")
    object UpdateUserInfo:EndPoints(path = "/update_user")
    object DeleteUser:EndPoints(path = "/delete_user")
    object SignOut:EndPoints(path = "/sign_out")
    object Unauthorized:EndPoints(path = "/unauthorized")
    object NoAvailableQuestion:EndPoints(path = "/unavailable")
    object Authorized:EndPoints(path = "/authorized")


}