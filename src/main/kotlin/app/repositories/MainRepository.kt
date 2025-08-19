package com.example.app.repositories

import com.example.app.models.Application
import com.example.app.models.BenefitType
import com.example.app.models.Dormitory
import com.example.app.models.EducationLevel
import com.example.app.models.Faculty
import com.example.app.models.FamilyMemberType
import com.example.app.models.FormOfEducation
import com.example.app.models.GenderType
import com.example.app.models.PassportType
import com.example.app.models.Room
import com.example.app.models.SettlementType
import com.example.app.models.SourceOfFunding
import com.example.app.models.Specialty
import com.example.app.models.StreetType
import com.example.app.models.University
import com.example.app.models.User
import com.example.database.entities.ApplicationEntity
import com.example.database.entities.BenefitTypeEntity
import com.example.database.entities.DormitoryEntity
import com.example.database.entities.EducationLevelEntity
import com.example.database.entities.FacultyEntity
import com.example.database.entities.FamilyMemberTypeEntity
import com.example.database.entities.FormOfEducationEntity
import com.example.database.entities.GenderTypeEntity
import com.example.database.entities.PassportTypeEntity
import com.example.database.entities.RoomEntity
import com.example.database.entities.SettlementTypeEntity
import com.example.database.entities.SourceOfFundingEntity
import com.example.database.entities.SpecialtyEntity
import com.example.database.entities.StreetTypeEntity
import com.example.database.entities.UniversityEntity
import com.example.database.entities.UserEntity
import com.example.db.suspendTransaction

class MainRepository {
    suspend fun allUsers(): List<User> = suspendTransaction {
        UserEntity.all().map(UserEntity::toModel)
    }

    suspend fun allApplications(): List<Application> = suspendTransaction {
        ApplicationEntity.all().map(ApplicationEntity::toModel)
    }

    suspend fun allBenefitTypes(): List<BenefitType> = suspendTransaction {
        BenefitTypeEntity.all().map(BenefitTypeEntity::toModel)
    }

    suspend fun benefitTypeById(id: Int?): BenefitType? = suspendTransaction {
        if (id == null) return@suspendTransaction null

        BenefitTypeEntity.findById(id)?.toModel()
    }

    suspend fun allDormitories(): List<Dormitory> = suspendTransaction {
        DormitoryEntity.all().map(DormitoryEntity::toModel)
    }

    suspend fun dormitoryById(id: Int?): Dormitory? = suspendTransaction {
        if (id == null) return@suspendTransaction null

        DormitoryEntity.findById(id)?.toModel()
    }

    suspend fun allEducationLevels(): List<EducationLevel> = suspendTransaction {
        EducationLevelEntity.all().map(EducationLevelEntity::toModel)
    }

    suspend fun educationLevelById(id: Int?): EducationLevel? = suspendTransaction {
        if (id == null) return@suspendTransaction null

        EducationLevelEntity.findById(id)?.toModel()
    }

    suspend fun allFaculties(): List<Faculty> = suspendTransaction {
        FacultyEntity.all().map(FacultyEntity::toModel)
    }

    suspend fun facultyById(id: Int?): Faculty? = suspendTransaction {
        if (id == null) return@suspendTransaction null

        FacultyEntity.findById(id)?.toModel()
    }

    suspend fun allFamilyMemberTypes(): List<FamilyMemberType> = suspendTransaction {
        FamilyMemberTypeEntity.all().map(FamilyMemberTypeEntity::toModel)
    }

    suspend fun allFormsOfEducation(): List<FormOfEducation> = suspendTransaction {
        FormOfEducationEntity.all().map(FormOfEducationEntity::toModel)
    }

    suspend fun formOfEducationById(id: Int?): FormOfEducation? = suspendTransaction {
        if (id == null) return@suspendTransaction null

        FormOfEducationEntity.findById(id)?.toModel()
    }

    suspend fun allGenderTypes(): List<GenderType> = suspendTransaction {
        GenderTypeEntity.all().map(GenderTypeEntity::toModel)
    }

    suspend fun genderTypeById(id: Int?): GenderType? = suspendTransaction {
        if (id == null) return@suspendTransaction null

        GenderTypeEntity.findById(id)?.toModel()
    }

    suspend fun allPassportTypes(): List<PassportType> = suspendTransaction {
        PassportTypeEntity.all().map(PassportTypeEntity::toModel)
    }

    suspend fun passportTypeById(id: Int?): PassportType? = suspendTransaction {
        if (id == null) return@suspendTransaction null

        PassportTypeEntity.findById(id)?.toModel()
    }

    suspend fun allRooms(): List<Room> = suspendTransaction {
        RoomEntity.all().map(RoomEntity::toModel)
    }

    suspend fun roomById(id: Int?): Room? = suspendTransaction {
        if (id == null) return@suspendTransaction null

        RoomEntity.findById(id)?.toModel()
    }

    suspend fun allSettlementTypes(): List<SettlementType> = suspendTransaction {
        SettlementTypeEntity.all().map(SettlementTypeEntity::toModel)
    }

        suspend fun settlementTypeById(id: Int?): SettlementType? = suspendTransaction {
        if (id == null) return@suspendTransaction null

        SettlementTypeEntity.findById(id)?.toModel()
    }

    suspend fun allSourcesOfFunding(): List<SourceOfFunding> = suspendTransaction {
        SourceOfFundingEntity.all().map(SourceOfFundingEntity::toModel)
    }

    suspend fun sourceOfFundingById(id: Int?): SourceOfFunding? = suspendTransaction {
        if (id == null) return@suspendTransaction null

        SourceOfFundingEntity.findById(id)?.toModel()
    }

    suspend fun allSpecialties(): List<Specialty> = suspendTransaction {
        SpecialtyEntity.all().map(SpecialtyEntity::toModel)
    }

    suspend fun specialtyById(id: Int?): Specialty? = suspendTransaction {
        if (id == null) return@suspendTransaction null

        SpecialtyEntity.findById(id)?.toModel()
    }

    suspend fun allStreetTypes(): List<StreetType> = suspendTransaction {
        StreetTypeEntity.all().map(StreetTypeEntity::toModel)
    }

    suspend fun streetTypeById(id: Int?): StreetType? = suspendTransaction {
        if (id == null) return@suspendTransaction null

        StreetTypeEntity.findById(id)?.toModel()
    }

    suspend fun allUniversities(): List<University> = suspendTransaction {
        UniversityEntity.all().map(UniversityEntity::toModel)
    }
}