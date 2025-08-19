package com.example.database.entities

import com.example.app.models.Specialty
import com.example.database.tables.SpecialtiesTable
import org.jetbrains.exposed.dao.ImmutableEntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID

class SpecialtyEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : ImmutableEntityClass<Int, SpecialtyEntity>(SpecialtiesTable)

    val name by SpecialtiesTable.name
    val createdAt by SpecialtiesTable.createdAt
    val updatedAt by SpecialtiesTable.updatedAt

    fun toModel() = Specialty(
        id = this.id.value,
        name = this.name,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
    )
}