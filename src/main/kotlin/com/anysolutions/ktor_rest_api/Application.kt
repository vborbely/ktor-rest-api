package com.anysolutions.ktor_rest_api

import com.anysolutions.ktor_rest_api.config.configureDatabase
import com.anysolutions.ktor_rest_api.error.configureErrorHandling
import com.anysolutions.ktor_rest_api.plugins.configureHTTP
import com.anysolutions.ktor_rest_api.plugins.configureRouting
import com.anysolutions.ktor_rest_api.plugins.configureSerialization
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.server.tomcat.*
import org.koin.ktor.ext.Koin
import org.koin.logger.slf4jLogger
import usersServicesModule

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.main() {
    configureDatabase()

    configureRouting()
    configureHTTP()
    configureSerialization()
    configureErrorHandling()
    install(DefaultHeaders)
    install(CallLogging)
    install(Koin) {
        slf4jLogger()
        modules(usersServicesModule)
    }
}
