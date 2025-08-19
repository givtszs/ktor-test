package com.example.database.entities

import com.example.app.models.SourceOfFunding
import com.example.database.tables.SourcesOfFundingTable
import org.jetbrains.exposed.dao.ImmutableEntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID

class SourceOfFundingEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : ImmutableEntityClass<Int, SourceOfFundingEntity>(SourcesOfFundingTable)

    val name by SourcesOfFundingTable.name
    val createdAt by SourcesOfFundingTable.createdAt
    val updatedAt by SourcesOfFundingTable.updatedAt

    fun toModel() = SourceOfFunding(
        id = this.id.value,
        name = this.name,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
    )
}