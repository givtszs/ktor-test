package com.example.app.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String,
    val email: String,
    val password: String,
    val emailVerifiedAt: String?,
    val createdAt: String?,
    val updatedAt: String?,
    val university: University,
    val photo: String?
)