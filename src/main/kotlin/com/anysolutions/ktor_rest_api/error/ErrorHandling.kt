package com.anysolutions.ktor_rest_api.error

import com.anysolutions.ktor_rest_api.domain.entities.ErrorDto
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import java.sql.SQLException
import java.time.Clock
import java.time.Instant

fun Application.configureErrorHandling() {
    install(StatusPages) {
        exception<SQLException> { cause ->
            val message = cause.cause?.message
            val shortMessage = message?.substring(0, message.indexOf(":"))

            call.respond(
                HttpStatusCode.BadRequest,
                ErrorDto(
                    "3000",
                    shortMessage ?: "Error happened",
                    shortMessage ?: "",
                    Instant.now(Clock.systemDefaultZone()).toHttpDateString()
                )
            )
        }
        exception<Throwable> { cause ->

            call.respond(
                HttpStatusCode.BadRequest,
                ErrorDto(
                    "3000",
                    cause.message ?: "Error happened",
                    cause.message ?: "",
                    Instant.now(Clock.systemDefaultZone()).toHttpDateString()
                )
            )
        }
        exception<CustomException> { ex ->
            call.respond(
                HttpStatusCode.BadRequest,
                ErrorDto(
                    ex.errorCode.code,
                    ex.errorCode.message,
                    ex.errorCode.details,
                    Instant.now(Clock.systemDefaultZone()).toHttpDateString()
                )
            )
        }
    }
}