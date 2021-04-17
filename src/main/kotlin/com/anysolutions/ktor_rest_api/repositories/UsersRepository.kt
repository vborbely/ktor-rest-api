package com.anysolutions.ktor_rest_api.repositories

import com.anysolutions.ktor_rest_api.domain.entities.User
import com.anysolutions.ktor_rest_api.domain.entities.UserEntity
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class UsersRepository {

    fun getAll(): List<User> {
        return transaction {
            UserEntity.selectAll().map {
                UserEntity.to(it)
            }.toList()
        }
    }

    fun insert(user: User): User {
        val savedUser = transaction {
            UserEntity.insert {
                it[extId] = UUID.randomUUID().toString()
                it[name] = user.name
                it[email] = user.email
                it[password] = user.password!!
                it[profilePhoto] = user.profilePhoto
            }
        }.resultedValues?.let { UserEntity.to(it.first()) }

        return savedUser!!
    }

    fun update(user: User): User {
        val updatedRows = transaction {
            UserEntity.update {
                Op.build { id eq user.id!!.toLong() }
                it[extId] = UUID.randomUUID().toString()
                it[name] = user.name
                it[email] = user.email
                it[password] = user.password!!
                it[profilePhoto] = user.profilePhoto
            }
        }

        return user
    }

    fun getById(id: Long): User? {
        return transaction {
            UserEntity.select(Op.build { UserEntity.id eq id }).map { UserEntity.to(it) }.firstOrNull()
        }
    }

    fun getByExtId(id: String): User? {
        return transaction {
            UserEntity.select(Op.build { UserEntity.extId eq id }).map { UserEntity.to(it) }.firstOrNull()
        }
    }

    fun delete(id: Long) {
        return transaction {
            UserEntity.deleteWhere { Op.build { UserEntity.id eq id } }
        }
    }

    fun deleteByExtId(id: String) {
        return transaction {
            UserEntity.deleteWhere { Op.build { UserEntity.extId eq id } }
        }
    }

}