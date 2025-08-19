package com.example.database.entities

import com.example.app.models.Dormitory
import com.example.database.tables.DormitoriesTable
import org.jetbrains.exposed.dao.ImmutableEntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID

class DormitoryEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : ImmutableEntityClass<Int, DormitoryEntity>(DormitoriesTable)

    val name by DormitoriesTable.name
    val address by DormitoriesTable.address
    val createdAt by DormitoriesTable.createdAt
    val updatedAt by DormitoriesTable.updatedAt

    fun toModel() = Dormitory(
        id = this.id.value,
        name = this.name,
        address = this.address,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
    )
}