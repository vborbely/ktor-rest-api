package com.anysolutions.ktor_rest_api.services

import com.anysolutions.ktor_rest_api.domain.entities.User
import com.anysolutions.ktor_rest_api.repositories.UsersRepository
import org.koin.java.KoinJavaComponent.inject


class UsersServices {
    private val usersRepository: UsersRepository by inject(clazz = UsersRepository::class.java)

    fun getAll(): List<User> {
        return usersRepository.getAll()
    }

    fun getById(id: Long): User? {
        return usersRepository.getById(id)
    }

    fun getByExtId(id: String): User? {
        return usersRepository.getByExtId(id)
    }

    fun save(user: User): User {
        return if (user.id != null) {
            usersRepository.update(user).copy(password = null, id = null)
        } else {
            usersRepository.insert(user).copy(password = null, id = null)
        }
    }

    fun deleteById(id: Long) {
        return usersRepository.delete(id)
    }

    fun deleteByExtId(id: String) {
        return usersRepository.deleteByExtId(id)
    }
}