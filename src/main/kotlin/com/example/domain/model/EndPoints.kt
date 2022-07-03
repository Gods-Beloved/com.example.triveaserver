package com.example.domain.model

sealed class EndPoints (val path:String){
    object Root:EndPoints(path = "/")
    object TokenVerification:EndPoints(path = "/token_verification")
    object GetUserInfo:EndPoints(path = "/get_user" )

    object SignUpUser:EndPoints(path = "/signup_user")
    object SignInUser:EndPoints(path = "/signin_user")
    object UpdateUserInfo:EndPoints(path = "/update_user")
    object DeleteUser:EndPoints(path = "/delete_user")
    object SignOut:EndPoints(path = "/sign_out")
    object Unauthorized:EndPoints(path = "/unauthorized")
    object Authorized:EndPoints(path = "/authorized")


}