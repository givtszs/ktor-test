package com.example.app.models

import kotlinx.serialization.Serializable

@Serializable
data class PassportData(
    val type: String,
    val birthPlace: String,
    val series: String,
    val number: String,
    val issuedBy: String,
    val dateIssued: String,
    val unzdr: String,
    val validity: String,
    val dateBirth: String,
    val typeOfGender: String,
    val photo1: String? = null,
    val photo2: String? = null,
    val photo3: String? = null,
)