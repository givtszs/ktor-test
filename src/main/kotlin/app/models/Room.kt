package com.example.app.models

import kotlinx.serialization.Serializable

@Serializable
data class Room(
    val id: Int,
    val number: String,
    val capacity: Int,
    val isActive: Boolean,
    val createdAt: String?,
    val updatedAt: String?,
)