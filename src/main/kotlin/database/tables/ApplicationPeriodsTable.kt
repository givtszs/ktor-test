package com.example.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object ApplicationPeriodsTable : IntIdTable("application_periods") {
    val name = varchar("name", 255)
    val startDate = datetime("start_date")
    val endDate = datetime("end_date")
    val isActive = bool("is_active").default(false)
    val createdAt = datetime("created_at").nullable()
    val updatedAt = datetime("updated_at").nullable()
    val applicationType = reference("application_type_id", ApplicationTypesTable)
}