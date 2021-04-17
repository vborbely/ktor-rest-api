package com.anysolutions.ktor_rest_api.config

import com.anysolutions.ktor_rest_api.domain.entities.UserEntity
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

fun configureDatabase() {
    Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;", "org.h2.Driver")
    transaction {
        SchemaUtils.create(UserEntity)

        // Dummy test data
        UserEntity.insert {
            it[extId] = "7575bcd3-6906-41f1-b32a-da6f2f301d73"
            it[name] = "Viktor Borbély"
            it[email] = "vborbely@example.com"
            it[password] = "password123"
            it[profilePhoto] = "profile URL"

        }
    }
}