package com.example.app.models

import kotlinx.serialization.Serializable

@Serializable
data class FormOfEducation(
    val id: Int,
    val name: String,
    val createdAt: String?,
    val updatedAt: String?,
)