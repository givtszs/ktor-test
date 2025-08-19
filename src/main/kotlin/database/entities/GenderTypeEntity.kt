package com.example.database.entities

import com.example.app.models.GenderType
import com.example.database.tables.GenderTypesTable
import org.jetbrains.exposed.dao.ImmutableEntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID

class GenderTypeEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : ImmutableEntityClass<Int, GenderTypeEntity>(GenderTypesTable)

    val name by GenderTypesTable.name
    val createdAt by GenderTypesTable.createdAt
    val updatedAt by GenderTypesTable.updatedAt

    fun toModel() = GenderType(
        id = this.id.value,
        name = this.name,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
    )
}