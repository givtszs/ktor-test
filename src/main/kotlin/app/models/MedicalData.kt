package com.example.app.models

import kotlinx.serialization.Serializable

@Serializable
data class MedicalData(
    val fluorographyLastDate: String,
    val fluorographyPhoto1: String? = null,
    val fluorographyPhoto2: String? = null,
    val medicalVaccinePhoto: String? = null,
)