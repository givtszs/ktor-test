package com.example.database.entities

import com.example.app.models.EducationLevel
import com.example.database.tables.EducationLevelsTable
import org.jetbrains.exposed.dao.ImmutableEntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID

class EducationLevelEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : ImmutableEntityClass<Int, EducationLevelEntity>(EducationLevelsTable)

    val name by EducationLevelsTable.name
    val createdAt by EducationLevelsTable.createdAt
    val updatedAt by EducationLevelsTable.updatedAt

    fun toModel() = EducationLevel(
        id = this.id.value,
        name = this.name,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
    )
}