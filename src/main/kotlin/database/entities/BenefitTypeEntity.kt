package com.example.database.entities

import com.example.app.models.BenefitType
import com.example.database.tables.BenefitTypesTable
import org.jetbrains.exposed.dao.ImmutableEntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID

class BenefitTypeEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : ImmutableEntityClass<Int, BenefitTypeEntity>(BenefitTypesTable)

    val name by BenefitTypesTable.name
    val createdAt by BenefitTypesTable.createdAt
    val updatedAt by BenefitTypesTable.updatedAt

    fun toModel() = BenefitType(
        id = this.id.value,
        name = this.name,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString()
    )
}