package com.example.database.entities

import com.example.app.models.StreetType
import com.example.database.tables.StreetTypesTable
import org.jetbrains.exposed.dao.ImmutableEntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID

class StreetTypeEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : ImmutableEntityClass<Int, StreetTypeEntity>(StreetTypesTable)

    val name by StreetTypesTable.name
    val createdAt by StreetTypesTable.createdAt
    val updatedAt by StreetTypesTable.updatedAt

    fun toModel() = StreetType(
        id = this.id.value,
        name = this.name,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
    )
}