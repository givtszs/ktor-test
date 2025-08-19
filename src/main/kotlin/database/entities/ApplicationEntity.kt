package com.example.database.entities

import com.example.app.models.Application
import com.example.app.models.ApplicationData
import com.example.database.tables.ApplicationsTable
import org.jetbrains.exposed.dao.ImmutableEntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID

class ApplicationEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : ImmutableEntityClass<Int, ApplicationEntity>(ApplicationsTable)

    val user by UserEntity referencedOn ApplicationsTable.user
    val applicationPeriod by ApplicationPeriodEntity referencedOn ApplicationsTable.applicationPeriod
    val status by ApplicationsTable.status
    val data by ApplicationsTable.data
    val createdAt by ApplicationsTable.createdAt
    val updatedAt by ApplicationsTable.updatedAt
    val corrections by ApplicationsTable.corrections
    val correctionsCount by ApplicationsTable.correctionsCount

    fun toModel() = Application(
        id = this.id.value,
        user = this.user.toModel(),
        status = this.status,
        data = this.data,
        corrections = this.corrections,
        correctionsCount = this.correctionsCount,
        updatedAt = this.updatedAt.toString(),
        createdAt = this.createdAt.toString(),
    )
}