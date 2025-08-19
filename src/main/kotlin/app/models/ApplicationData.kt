package com.example.app.models

import kotlinx.serialization.Serializable

@Serializable
data class ApplicationData(
    val firstName: String,
    val secondName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val photo: String? = null,
    val passport: PassportData,
    val education: EducationData,
    val address: AddressData,
    val residence: AddressData,
    val residenceAddressDifferent: Boolean,
    val familyMembersData: String,
    val familyPhoto: String? = null,
    val privilege: PrivilegeData,
    val medicalData: MedicalData,
    val appealToAdministration: String,
    val selectedDormitory: String,
    val selectedRoom: String,
    val consent: Boolean,
) {
//    fun ApplicationData.fromDto(dto: ApplicationDataDto) = ApplicationData(
//        firstName = dto.firstName ?: "",
//        secondName = dto.secondName ?: "",
//        lastName = dto.lastName ?: "",
//        email = dto.email ?: "",
//        phoneNumber = dto.phoneNumber ?: "",
//        photo = dto.photo ?: "",
//        passport = PasportData(
//            type = dto.passport_
//        ),
//    )
}