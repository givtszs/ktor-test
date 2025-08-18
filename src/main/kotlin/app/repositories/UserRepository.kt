package com.example.app.repositories

import com.example.app.models.User
import com.example.app.repositories.contracts.UserRepositoryContract
import com.example.database.entities.UserEntity
import com.example.db.suspendTransaction

class UserRepository : UserRepositoryContract {
    override suspend fun allUsers(): List<User> = suspendTransaction {
        UserEntity.all().map(UserEntity::toModel)
    }
}