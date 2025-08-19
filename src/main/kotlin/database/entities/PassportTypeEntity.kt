package com.example.database.entities

import com.example.app.models.PassportType
import com.example.database.tables.PassportTypesTable
import org.jetbrains.exposed.dao.ImmutableEntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID

class PassportTypeEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : ImmutableEntityClass<Int, PassportTypeEntity>(PassportTypesTable)

    val name by PassportTypesTable.name
    val createdAt by PassportTypesTable.createdAt
    val updatedAt by PassportTypesTable.updatedAt

    fun toModel() = PassportType(
        id = this.id.value,
        name = this.name,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
    )
}