package com.example.app.models

import kotlinx.serialization.Serializable

@Serializable
data class AddressData(
    val region: String,
    val district: String,
    val settlement: String,
    val city: String,
    val streetType: String,
    val street: String,
    val house: String,
    val apartment: String,
)
