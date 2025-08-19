package com.example.app.models

import kotlinx.serialization.Serializable

@Serializable
data class Dormitory(
    val id: Int,
    val name: String,
    val address: String,
    val createdAt: String?,
    val updatedAt: String?,
)