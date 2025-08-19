package com.example.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object RoomsTable : IntIdTable("rooms") {
    val dormitory = reference("dormitory_id", DormitoriesTable)
    val number = varchar("number", 50)
    val capacity = integer("capacity")
    val isActive = bool("is_active").default(true)
    val createdAt = datetime("created_at").nullable()
    val updatedAt = datetime("updated_at").nullable()
}