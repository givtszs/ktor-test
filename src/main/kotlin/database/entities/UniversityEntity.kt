package com.example.database.entities

import com.example.app.models.University
import com.example.database.tables.UniversitiesTable
import org.jetbrains.exposed.dao.ImmutableEntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID

class UniversityEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : ImmutableEntityClass<Int, UniversityEntity>(UniversitiesTable)

    val fullName by UniversitiesTable.fullName
    val shortName by UniversitiesTable.shortName
    val isActive by UniversitiesTable.isActive
    val createdAt by UniversitiesTable.createdAt
    val updatedAt by UniversitiesTable.updatedAt

    fun toModel() = University(
        id = this.id.value,
        fullName = this.fullName,
        shortName = this.shortName,
        isActive = this.isActive,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
    )
}