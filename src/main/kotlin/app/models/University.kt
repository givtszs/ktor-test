package com.example.app.models

import kotlinx.serialization.Serializable

@Serializable
data class University(
    val fullName: String,
    val shortName: String,
    val createdAt: String?,
    val updatedAt: String?,
    val isActive: Boolean
)