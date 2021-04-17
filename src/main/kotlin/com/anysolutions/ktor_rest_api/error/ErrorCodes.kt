package com.anysolutions.ktor_rest_api.error

enum class ErrorCodes(
    val code: String,
    val message: String,
    val details: String
) {

    E_MISSING_ID("3001", "No Id provided", "No Id provided")

}