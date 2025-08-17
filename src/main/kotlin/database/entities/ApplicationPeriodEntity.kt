package com.example.database.entities

import com.example.database.tables.ApplicationPeriodsTable
import org.jetbrains.exposed.dao.ImmutableEntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID

class ApplicationPeriodEntity(id : EntityID<Int>) : IntEntity(id) {
    companion object : ImmutableEntityClass<Int, ApplicationPeriodEntity>(ApplicationPeriodsTable)

    val name by ApplicationPeriodsTable.name
    val startDate by ApplicationPeriodsTable.startDate
    val endDate by ApplicationPeriodsTable.endDate
    val isActive by ApplicationPeriodsTable.isActive
    val applicationType by ApplicationTypeEntity referencedOn ApplicationPeriodsTable.applicationType
    val createdAt by ApplicationPeriodsTable.createdAt
    val updatedAt by ApplicationPeriodsTable.updatedAt
}