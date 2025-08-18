package com.example.routes

import com.example.app.repositories.contracts.UserRepositoryContract
import com.example.app.repositories.contracts.app.repositories.contracts.ApplicationRepositoryContract
import io.ktor.http.HttpStatusCode
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

            get("/{id}/data") {
                val id = call.parameters["id"]

                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, "Application id is null")
                    return@get
                }

                val application = applicationRepository.applicationById(id.toInt())

                if (application == null) {
                    call.respond(HttpStatusCode.NotFound, "Application with id $id not found")
                    return@get
                }

                call.respond(application)
            }
        }
    }
}