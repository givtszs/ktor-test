package com.example.database.entities

import com.example.app.models.FormOfEducation
import com.example.database.tables.FormsOfEducationTable
import org.jetbrains.exposed.dao.ImmutableEntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID

class FormOfEducationEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : ImmutableEntityClass<Int, FormOfEducationEntity>(FormsOfEducationTable)

    val name by FormsOfEducationTable.name
    val createdAt by FormsOfEducationTable.createdAt
    val updatedAt by FormsOfEducationTable.updatedAt

    fun toModel() = FormOfEducation(
        id = this.id.value,
        name = this.name,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
    )
}