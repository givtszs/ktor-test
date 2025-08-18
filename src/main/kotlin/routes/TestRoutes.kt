package com.example.routes

import com.example.app.repositories.contracts.UserRepositoryContract
import com.example.app.repositories.contracts.app.repositories.contracts.ApplicationRepositoryContract
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureTestRoutes(
    userRepository: UserRepositoryContract,
    applicationRepository: ApplicationRepositoryContract
) {
    routing {
        route("/users") {
            get {
                val users = userRepository.allUsers()
                call.respond(users)
            }
        }

        route("/applications") {
            get {
                val applications = applicationRepository.allApplications()
                call.respond(applications)
            }
        }
    }
}