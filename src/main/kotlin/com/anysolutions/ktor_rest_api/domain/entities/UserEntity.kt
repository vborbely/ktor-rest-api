package com.anysolutions.ktor_rest_api.domain.entities

import com.anysolutions.ktor_rest_api.domain.dto.UserDto
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object UserEntity : Table(name = "users") {
    val id: Column<Long> = long("id").autoIncrement()
    val extId: Column<String> = varchar("ext_id", 50).uniqueIndex("uidx_user_extid")
    val name: Column<String> = varchar("name", 50)
    val email: Column<String> = varchar("email", 255).uniqueIndex("uidx_user_email")
    val password: Column<String> = varchar("password", 200)
    val profilePhoto: Column<String?> = varchar("profile_photo", 255).nullable()

    override val primaryKey = PrimaryKey(id, name = "pk_user_id")

    fun dto(row: ResultRow): UserDto {
        return UserDto(row[id], row[extId], row[name], row[email], row[password], row[profilePhoto])
    }

    fun to(entity: ResultRow): User {
        return User(entity[id], entity[extId], entity[name], entity[email], entity[password], entity[profilePhoto])
    }
}

@Serializable
data class User(
    val id: Long?,
    val extId: String?,
    val name: String,
    val email: String,
    val password: String?,
    val profilePhoto: String?
)