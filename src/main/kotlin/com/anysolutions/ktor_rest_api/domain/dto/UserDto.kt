package com.anysolutions.ktor_rest_api.domain.dto

import com.anysolutions.ktor_rest_api.domain.entities.User
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable
import org.jetbrains.annotations.Nullable
import org.valiktor.functions.hasSize
import org.valiktor.functions.isEmail
import org.valiktor.functions.isPositive
import org.valiktor.validate

@Serializable
@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserDto(
    @JsonIgnore
    @Nullable
    val id: Long?,
    @Nullable
    val extId: String?,
    val name: String,
    val email: String,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val password: String?,
    val profilePhoto: String?
) {

    init {
        validate(this) {
            validate(UserDto::id).isPositive()
            validate(UserDto::email).hasSize(min = 3, max = 255).isEmail()
            validate(UserDto::name).hasSize(min = 3, max = 50)
            validate(UserDto::password).hasSize(min = 3, max = 200)
            validate(UserDto::profilePhoto).hasSize(max = 255)
        }
    }

    fun to(): User {
        return User(this.id, this.extId, this.name, this.email, this.password, this.profilePhoto)
    }
}