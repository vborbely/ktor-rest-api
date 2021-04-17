package com.anysolutions.ktor_rest_api

import io.ktor.http.*
import io.ktor.server.testing.*
import com.anysolutions.ktor_rest_api.plugins.configureRouting
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ configureRouting() }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("You can use this API set by calling /api/* endpoints", response.content)
            }
        }
    }
}