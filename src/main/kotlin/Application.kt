package com.example

import com.example.app.repositories.UserRepository
import com.example.app.repositories.ApplicationRepository
import com.example.app.repositories.MainRepository
import com.example.app.services.ReportsService
import com.example.model.SQLiteTaskRepository
import com.example.routes.configureReportsRouting
import com.example.routes.configureTestRoutes
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
//    val repository = PostgresTaskRepository()
    val repository = SQLiteTaskRepository()
    val userRepository = UserRepository()
    val applicationRepository = ApplicationRepository()
    val mainRepository = MainRepository()

    val reportsService = ReportsService(mainRepository, applicationRepository)

    configureSerialization(repository, userRepository)
//    configureDatabases()
    configureSQLiteDatabase("data/database.sqlite")
    configureRouting()
    configureReportsRouting(repository, reportsService)
    configureTestRoutes(userRepository, applicationRepository)
}