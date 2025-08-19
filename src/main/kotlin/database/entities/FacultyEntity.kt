package com.example.database.entities

import com.example.app.models.Faculty
import com.example.database.tables.FacultiesTable
import org.jetbrains.exposed.dao.ImmutableEntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID

class FacultyEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : ImmutableEntityClass<Int, FacultyEntity>(FacultiesTable)

    val name by FacultiesTable.name
    val createdAt by FacultiesTable.createdAt
    val updatedAt by FacultiesTable.updatedAt

    fun toModel() = Faculty(
        id = this.id.value,
        name = this.name,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
    )
}