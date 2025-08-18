package com.example.app.repositories.contracts.app.repositories.contracts

import com.example.app.models.Application

interface ApplicationRepositoryContract {
    suspend fun allApplications(): List<Application>
    suspend fun applicationById(id: Int): Application?
}