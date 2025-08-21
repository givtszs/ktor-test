package com.example.app.services

import com.example.app.models.AddressData
import com.example.app.models.ApplicationData
import com.example.app.models.ApplicationDataDto
import com.example.app.models.EducationData
import com.example.app.models.MedicalData
import com.example.app.models.PassportData
import com.example.app.models.PrivilegeData
import com.example.app.repositories.MainRepository
import com.example.app.repositories.contracts.ApplicationRepositoryContract

class ReportsService(
    private val mainRepository: MainRepository,
    private val applicationRepository: ApplicationRepositoryContract
) {
    suspend fun prepareApplicationData(applicationDataDto: ApplicationDataDto): ApplicationData {
//        val application = applicationRepository.applicationById(applicationId)
//
//        if (application == null) {
//            throw Exception("Application with id $applicationId not found")
//        }

        val passportType = mainRepository.passportTypeById(applicationDataDto.passport_typeOfPassport_id?.toInt())
        val genderType = mainRepository.genderTypeById(applicationDataDto.typeOfGenderOfPerson_id?.toInt())
        val educationLevel = mainRepository.educationLevelById(applicationDataDto.selectedQualification?.toInt())
        val faculty = mainRepository.facultyById(applicationDataDto.faculty_id?.toInt())
        val specialty = mainRepository.specialtyById(applicationDataDto.specialization_id?.toInt())
        val sourceOfFunding = mainRepository.sourceOfFundingById(applicationDataDto.financialSource_id?.toInt())
        val formOfEducation = mainRepository.formOfEducationById(applicationDataDto.formOfEducation_id?.toInt())
        val addressSettlementType = mainRepository.settlementTypeById(applicationDataDto.address_typeOfCity_id?.toInt())
        val addressStreetType = mainRepository.streetTypeById(applicationDataDto.address_typeOfStreet_id?.toInt())
        val residenceSettlementType = mainRepository.settlementTypeById(applicationDataDto.residence_typeOfCity_id?.toInt())
        val residenceStreetType = mainRepository.streetTypeById(applicationDataDto.residence_typeOfStreet_id?.toInt())
        val benefitType = mainRepository.benefitTypeById(applicationDataDto.privilege_typeOfPrivilege_id?.toInt())
        val selectedDormitory = mainRepository.dormitoryById(applicationDataDto.selectedDormitory?.toInt())
        val selectedRoom = mainRepository.roomById(applicationDataDto.selectedRoom?.toInt())

        return ApplicationData(
            firstName = applicationDataDto.firstName ?: "",
            secondName = applicationDataDto.secondName ?: "",
            lastName = applicationDataDto.lastName ?: "",
            email = applicationDataDto.email ?: "",
            phoneNumber = applicationDataDto.phoneNumber ?: "",
            photo = applicationDataDto.photo ?: "",
            passport = PassportData(
                type = passportType?.name ?: "",
                birthPlace = applicationDataDto.passport_birthPlace ?: "",
                series = applicationDataDto.passport_series ?: "",
                number = applicationDataDto.passport_number ?: "",
                issuedBy = applicationDataDto.passport_issuedBy ?: "",
                dateIssued = applicationDataDto.passport_dateIssued ?: "",
                unzdr = applicationDataDto.passport_unzdr ?: "",
                validity = applicationDataDto.passport_validity ?: "",
                dateBirth = applicationDataDto.dateBirth ?: "",
                typeOfGender = genderType?.name ?: "",
                photo1 = applicationDataDto.passportPhoto1 ?: "",
                photo2 = applicationDataDto.passportPhoto2 ?: "",
                photo3 = applicationDataDto.passportPhoto3 ?: "",
            ),
            education = EducationData(
                educationLevel = educationLevel?.name ?: "",
                course = applicationDataDto.course ?: "",
                faculty = faculty?.name ?: "",
                specialty = specialty?.name ?: "",
                sourceOfFunding = sourceOfFunding?.name ?: "",
                formOfEducation = formOfEducation?.name ?: "",
            ),
            address = AddressData(
                region = applicationDataDto.address_region ?: "",
                district = applicationDataDto.address_district ?: "",
                settlement = addressSettlementType?.name ?: "",
                city = applicationDataDto.address_city ?: "",
                streetType = addressStreetType?.name ?: "",
                street = applicationDataDto.address_street ?: "",
                house = applicationDataDto.address_house ?: "",
                apartment = applicationDataDto.address_apartment ?: "",
            ),
            residence = AddressData(
                region = applicationDataDto.residence_region ?: "",
                district = applicationDataDto.residence_district ?: "",
                settlement = residenceSettlementType?.name ?: "",
                city = applicationDataDto.residence_city ?: "",
                streetType = residenceStreetType?.name ?: "",
                street = applicationDataDto.residence_street ?: "",
                house = applicationDataDto.residence_house ?: "",
                apartment = applicationDataDto.residence_apartment ?: "",
            ),
            residenceAddressDifferent = applicationDataDto.residenceAddressDifferent?.toBoolean() ?: false,
            familyMembersData = applicationDataDto.familyMembersData ?: "",
            familyPhoto = applicationDataDto.familyPhoto ?: "",
            privilege = PrivilegeData(
                exists = applicationDataDto.privilegeExists?.toBoolean() ?: false,
                type = benefitType?.name ?: "",
                other = applicationDataDto.privilege_otherPrivilege ?: "",
                photo1 = applicationDataDto.privilegePhoto1,
                photo2 = applicationDataDto.privilegePhoto2,
                photo3 = applicationDataDto.privilegePhoto3,
            ),
            medicalData = MedicalData(
                fluorographyLastDate = applicationDataDto.fluorography_lastDate ?: "",
                fluorographyPhoto1 = applicationDataDto.fluorographyPhoto1,
                fluorographyPhoto2 = applicationDataDto.fluorographyPhoto2,
                medicalVaccinePhoto =  applicationDataDto.medicalVaccinePhoto,
            ),
            appealToAdministration = applicationDataDto.appealToAdministration ?: "",
            selectedDormitory = selectedDormitory?.name ?: "",
            selectedRoom = selectedRoom?.number ?: "",
            consent = applicationDataDto.consent?.toBoolean() ?: false
        )
    }
}