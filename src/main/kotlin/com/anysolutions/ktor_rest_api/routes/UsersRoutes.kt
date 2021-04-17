package com.anysolutions.ktor_rest_api.routes

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import com.anysolutions.ktor_rest_api.domain.dto.UserDto
import com.anysolutions.ktor_rest_api.services.UsersServices
import org.koin.ktor.ext.inject


fun Application.usersRoutes(api: String) {
    routing {
        getAllUser(api)
        getUserById(api)
        createUser(api)
        updateUser(api)
        deleteUser(api)
    }
}


fun Route.getAllUser(api: String) {
    val userService by inject<UsersServices>()

    get(api) {
        val users = userService.getAll()
        call.respond(
            users.map {
                UserDto(
                    it.id,
                    it.extId,
                    it.name,
                    it.email,
                    it.password,
                    it.profilePhoto
                )
            }
        )
    }
}

fun Route.getUserById(api: String) {
    val userService by inject<UsersServices>()

    get("$api/{id}") {
        call.parameters["id"]?.let {
            call.respond(
                userService.getById(it.toLong())
            )
        }

        throw Exception("No Id provided")
    }
}

fun Route.createUser(api: String) {
    val userService by inject<UsersServices>()

    post(api) {
        val userDto = call.receive<UserDto>()

        call.respond(
            userService.save(userDto.to())
        )
    }
}

fun Route.updateUser(api: String) {
    val userService by inject<UsersServices>()

    put(api) {
        val userDto = call.receive<UserDto>()

        call.respond(
            userService.save(userDto.to())
        )
    }
}

fun Route.deleteUser(api: String) {
    val userService by inject<UsersServices>()

    delete("$api/{extId}") {
        call.parameters["extId"]?.let {
            call.respond(
                userService.deleteByExtId(it)
            )
        }

        throw Exception("No Id provided")
    }
}
