package com.example

import com.example.model.PostgresTaskRepository
import com.example.model.SQLiteTaskRepository
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
//    val repository = PostgresTaskRepository()
    val repository = SQLiteTaskRepository()

    configureSerialization(repository)
//    configureDatabases()
    configureSQLiteDatabase()
    configureRouting()
    configureReportsRouting(repository)
}