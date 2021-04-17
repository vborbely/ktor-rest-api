package com.anysolutions.ktor_rest_api.routes

import com.anysolutions.ktor_rest_api.domain.dto.UserDto
import com.anysolutions.ktor_rest_api.error.CustomException
import com.anysolutions.ktor_rest_api.error.ErrorCodes.E_MISSING_ID
import com.anysolutions.ktor_rest_api.services.UsersServices
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject


fun Application.usersRoutes(api: String) {
    routing {
        getAllUser(api)
        getUserByExtId(api)
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

fun Route.getUserByExtId(api: String) {
    val userService by inject<UsersServices>()

    get("$api/{id}") {
        call.parameters["id"]?.let {
            userService.getByExtId(it)?.let { user -> call.respond(user) }
        }

        throw Exception("No Id provided")
    }
}

fun Route.createUser(api: String) {
    val userService by inject<UsersServices>()

    post(api) {
        val userDto = call.receive<UserDto>()

        if (userDto.extId != null || userDto.id != null) {
            throw Exception("New entity cannot have an Id")
        }

        call.respond(
            userService.save(userDto.to())
        )
    }
}

fun Route.updateUser(api: String) {
    val userService by inject<UsersServices>()

    put(api) {
        val userDto = call.receive<UserDto>()

        if (userDto.extId == null) {
            throw CustomException(E_MISSING_ID)
        }

        val userToSave = userService.getByExtId(userDto.extId)

        userToSave?.let {
            val save = it.copy(email = userDto.email, name = userDto.name)

            call.respond(
                userService.save(save)
            )
        }

        call.respond(HttpStatusCode.NotFound)
    }
}

fun Route.deleteUser(api: String) {
    val userService by inject<UsersServices>()

    delete("$api/{id}") {
        call.parameters["id"]?.let {
            call.respond(
                userService.deleteByExtId(it)
            )
        }

        throw CustomException(E_MISSING_ID)
    }
}
