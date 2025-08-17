package com.example.database.entities

import com.example.database.tables.ApplicationTypesTable
import org.jetbrains.exposed.dao.ImmutableEntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID

class ApplicationTypeEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : ImmutableEntityClass<Int, ApplicationTypeEntity>(ApplicationTypesTable)

    val name by ApplicationTypesTable.name
    val description by ApplicationTypesTable.description
    val createdAt by ApplicationTypesTable.createdAt
    val updatedAt by ApplicationTypesTable.updatedAt
}