package com.anysolutions.ktor_rest_api.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class ErrorDto(val code: String, val message: String, val details: String, val time: String)