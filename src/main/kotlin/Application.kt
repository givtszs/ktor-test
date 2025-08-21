package com.example

import com.example.app.repositories.UserRepository
import com.example.app.repositories.ApplicationRepository
import com.example.app.repositories.MainRepository
import com.example.app.services.ReportsService
import com.example.model.SQLiteTaskRepository
import com.example.routes.reports.configureReportsRouting
import com.example.routes.configureTestRoutes
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.CORS
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun formatDate(input: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val date = LocalDate.parse(input, inputFormatter)
    return date.format(outputFormatter)
}

fun Application.module() {
    install(CORS) {
        anyHost() // allow all origins
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Post)
        allowHeader(HttpHeaders.ContentType)
    }
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