package com.example.database.tables

import com.example.app.models.ApplicationDataDto
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.json.json
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

val mapper = jacksonObjectMapper()

object ApplicationsTable : IntIdTable("applications") {
    val user = reference("user_id", UsersTable)
    val applicationPeriod = reference("application_period_id", ApplicationPeriodsTable)
    val status = varchar("status", 50).default("new")
    val data = json(
        "data",
        { mapper.writeValueAsString(it) },
        { mapper.readValue<ApplicationDataDto>(it) }
    )
    val createdAt = datetime("created_at").nullable()
    val updatedAt = datetime("updated_at").nullable()
    val corrections = text("corrections").nullable()
    val correctionsCount = integer("corrections_count").default(0)
}
