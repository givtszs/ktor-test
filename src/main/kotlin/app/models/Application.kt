package com.example.app.models

import kotlinx.serialization.Serializable

@Serializable
data class Application(
    val id: Int,
    val user: User,
    val status: String,
    val data: ApplicationDataDto,
    val createdAt: String?,
    val updatedAt: String?,
    val corrections: String?,
    val correctionsCount: Int
)