package com.example.database.entities

import com.example.app.models.SettlementType
import com.example.database.tables.SettlementTypesTable
import org.jetbrains.exposed.dao.ImmutableEntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID

class SettlementTypeEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : ImmutableEntityClass<Int, SettlementTypeEntity>(SettlementTypesTable)

    val name by SettlementTypesTable.name
    val createdAt by SettlementTypesTable.createdAt
    val updatedAt by SettlementTypesTable.updatedAt

    fun toModel() = SettlementType(
        id = this.id.value,
        name = this.name,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
    )
}