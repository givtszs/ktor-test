package com.example.app.models

import kotlinx.serialization.Serializable

@Serializable
data class Application(
    val user: User,
    val status: String,
    val data: ApplicationData,
    val createdAt: String?,
    val updatedAt: String?,
    val corrections: String?,
    val correctionsCount: Int
)