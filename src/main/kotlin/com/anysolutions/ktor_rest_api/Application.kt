package com.anysolutions.ktor_rest_api

import com.anysolutions.ktor_rest_api.config.configureDatabase
import com.anysolutions.ktor_rest_api.plugins.configureHTTP
import com.anysolutions.ktor_rest_api.plugins.configureRouting
import com.anysolutions.ktor_rest_api.plugins.configureSerialization
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.server.engine.*
import io.ktor.server.tomcat.*
import org.koin.ktor.ext.Koin
import org.koin.logger.slf4jLogger
import usersServicesModule

fun main() {
    embeddedServer(
        Tomcat,
        port = 8080,
        host = "0.0.0.0"
//        , watchPaths = listOf("classes")  // debug mode
    )
    {
        main()
    }.start(wait = true)
}

fun Application.main() {
    configureDatabase()

    configureRouting()
    configureHTTP()
    configureSerialization()
    install(DefaultHeaders)
    install(CallLogging)
    install(Koin) {
        slf4jLogger()
        modules(usersServicesModule)
    }
}
