package com.example.database.entities

import com.example.app.models.User
import com.example.database.tables.UsersTable
import org.jetbrains.exposed.dao.ImmutableEntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID

class UserEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : ImmutableEntityClass<Int, UserEntity>(UsersTable)

    val name by UsersTable.name
    val email by UsersTable.email
    val password by UsersTable.password
    val emailVerifiedAt by UsersTable.emailVerifiedAt
    val createdAt by UsersTable.createdAt
    val updatedAt by UsersTable.updatedAt
    val photo by UsersTable.photo
    val university by UniversityEntity optionalReferencedOn UsersTable.university

    fun toModel() = User(
        name = this.name,
        email = this.email,
        password = this.password,
        photo = this.photo,
        university = this.university?.toModel(),
        emailVerifiedAt = this.emailVerifiedAt.toString(),
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
    )
}