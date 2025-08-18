package com.example

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.*
import java.io.File
import java.sql.Connection
import java.sql.DriverManager

fun Application.configureSQLiteDatabase(dbPath: String = "data/tasks.db"): Database {
    val database = Database.connect(
        url = "jdbc:sqlite:$dbPath",
        driver = "org.sqlite.JDBC"
    )

    return database
}

/**
 * Alternative method for direct SQLite connection (if needed)
 */
fun Application.connectToSQLite(dbPath: String = "data/tasks.db"): Connection {
    Class.forName("org.sqlite.JDBC")

    val dbFile = File(dbPath)
    dbFile.parentFile?.mkdirs()

    return DriverManager.getConnection("jdbc:sqlite:$dbPath")
}