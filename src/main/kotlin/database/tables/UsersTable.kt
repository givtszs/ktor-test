package com.example.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object UsersTable : IntIdTable("users") {
    val name = varchar("name", 255)
    val email = varchar("email", 255)
    val password = varchar("password", 255)
    val emailVerifiedAt = datetime("email_verified_at").nullable()
    val createdAt = datetime("created_at").nullable()
    val updatedAt = datetime("updated_at").nullable()
    val photo = varchar("user_photo", 500).nullable()
    val university = reference("university_id", UniversitiesTable)
}