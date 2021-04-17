package com.anysolutions.ktor_rest_api.error

//class CustomException(
//    val code: String,
//    message: String,
//    val details: String
//) : Exception(message)

class CustomException(val errorCode: ErrorCodes) : Exception(errorCode.message)