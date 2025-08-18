package com.example.app.repositories.app.repositories

import com.example.app.models.Application
import com.example.app.repositories.contracts.app.repositories.contracts.ApplicationRepositoryContract
import com.example.database.entities.ApplicationEntity
import com.example.db.suspendTransaction

class ApplicationRepository : ApplicationRepositoryContract {
    override suspend fun allApplications(): List<Application> = suspendTransaction {
        ApplicationEntity.all().map(ApplicationEntity::toModel)
    }

    override suspend fun applicationById(id: Int): Application? = suspendTransaction {
        ApplicationEntity.findById(id)?.toModel()
    }
}