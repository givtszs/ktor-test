package com.example.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object UniversitiesTable : IntIdTable("universities") {
    val fullName = varchar("full_name", 255)
    val shortName = varchar("short_name", 255)
    val isActive = bool("is_active").default(false)
    val createdAt = datetime("created_at").nullable()
    val updatedAt = datetime("updated_at").nullable()
}