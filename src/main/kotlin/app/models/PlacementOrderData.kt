package com.example.app.models

import kotlinx.serialization.Serializable

@Serializable
data class PlacementOrderData(
    val firstName: String,
    val secondName: String,
    val lastName: String,
    val faculty: String,
    val educationLevel: String,
    val course: String,
    val dormitory: String,
    val room: String,
)
