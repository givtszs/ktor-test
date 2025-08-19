package com.example.database.entities

import com.example.app.models.Room
import com.example.database.tables.RoomsTable
import org.jetbrains.exposed.dao.ImmutableEntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID

class RoomEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : ImmutableEntityClass<Int, RoomEntity>(RoomsTable)

    val dormitory by DormitoryEntity referencedOn RoomsTable.dormitory
    val number by RoomsTable.number
    val capacity by RoomsTable.capacity
    val isActive by RoomsTable.isActive
    val createdAt by RoomsTable.createdAt
    val updatedAt by RoomsTable.updatedAt

    fun toModel() = Room(
        id = this.id.value,
        number = this.number,
        capacity = this.capacity,
        isActive = this.isActive,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
    )
}