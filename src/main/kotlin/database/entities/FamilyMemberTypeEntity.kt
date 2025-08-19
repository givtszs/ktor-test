package com.example.database.entities

import com.example.app.models.FamilyMemberType
import com.example.database.tables.FamilyMemberTypesTable
import org.jetbrains.exposed.dao.ImmutableEntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID

class FamilyMemberTypeEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : ImmutableEntityClass<Int, FamilyMemberTypeEntity>(FamilyMemberTypesTable)

    val name by FamilyMemberTypesTable.name
    val createdAt by FamilyMemberTypesTable.createdAt
    val updatedAt by FamilyMemberTypesTable.updatedAt

    fun toModel() = FamilyMemberType(
        id = this.id.value,
        name = this.name,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
    )
}