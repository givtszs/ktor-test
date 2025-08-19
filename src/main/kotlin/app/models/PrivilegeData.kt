package com.example.app.models

import kotlinx.serialization.Serializable

@Serializable
data class PrivilegeData(
    val exists: Boolean,
    val type: String? = null,
    val other: String? = null,
    val photo1: String? = null,
    val photo2: String? = null,
    val photo3: String? = null,
)
