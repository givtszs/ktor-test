package com.example.app.repositories.contracts

import com.example.app.models.User
import com.example.database.entities.UserEntity
import com.example.db.suspendTransaction

interface UserRepositoryContract {
    suspend fun allUsers(): List<User>
}