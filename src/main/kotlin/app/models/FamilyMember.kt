package com.example.app.models

import kotlinx.serialization.Serializable

@Serializable
data class FamilyMember(
    val fullName: String? = null,
    val phoneNumber: String? = null,
    val typeOfFamilyMember_id: String? = null
)
