package com.example.app.models

import kotlinx.serialization.Serializable

@Serializable
data class EducationLevel(
    val id: Int,
    val name: String,
    val createdAt: String? = null,
    val updatedAt: String? = null,
)