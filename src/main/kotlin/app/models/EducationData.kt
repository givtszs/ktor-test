package com.example.app.models

import kotlinx.serialization.Serializable

@Serializable
data class EducationData(
    val educationLevel: String,
    val course: String,
    val faculty: String,
    val specialty: String,
    val sourceOfFunding: String,
    val formOfEducation: String,
)