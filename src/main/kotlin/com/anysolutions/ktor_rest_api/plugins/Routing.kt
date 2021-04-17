package com.anysolutions.ktor_rest_api.plugins

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import com.anysolutions.ktor_rest_api.routes.usersRoutes


const val UsersApi = "/api/users"

fun Application.configureRouting() {


    routing {
        get("/") {
            call.respondText("You can use this API set by calling /api/* endpoints")
        }
    }
    usersRoutes(UsersApi)

}

