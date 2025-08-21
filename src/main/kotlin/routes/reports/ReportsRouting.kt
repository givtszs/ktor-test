package com.example.routes.reports

import com.example.app.models.PlacementOrderData
import com.example.app.services.ReportsService
import com.example.model.Priority
import com.example.model.TaskRepository
import com.example.service.JasperReportService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun Application.configureReportsRouting(repository: TaskRepository, reportsService: ReportsService) {
    val reportService = JasperReportService()

    routing {
        route("/reports") {
//            get("/{applicationId}/placement-order") {
//                try {
//                    val id = call.parameters["applicationId"]
//
//                    if (id == null || id.isEmpty()) {
//                        call.respond(HttpStatusCode.BadRequest, "Application id is null")
//                        return@get
//                    }
//
//                    val applicationData = reportsService.prepareApplicationData(id.toInt())
//                    val placementOrderData = PlacementOrderData(
//                        firstName = applicationData.firstName,
//                        secondName = applicationData.secondName,
//                        lastName = applicationData.lastName,
//                        faculty = applicationData.education.faculty,
//                        course = applicationData.education.course,
//                        educationLevel = applicationData.education.educationLevel,
//                        dormitory = applicationData.selectedDormitory.split(" ")[1], // take only number
//                        room = applicationData.selectedRoom,
//                        fluorographyLastDate = applicationData.medicalData.fluorographyLastDate
//                    )
//
////                    call.respond(placementOrderData)
////                    return@get
//
//                    val placementOrderBytes = withContext(Dispatchers.IO) {
//                        reportService.generatePlacementOrderPdf(placementOrderData)
//                    }
//
//                    call.response.header(
//                        HttpHeaders.ContentDisposition,
//                        "attachment; filename=\"placement_order.pdf\""
//                    )
//                    call.respondBytes(
//                        placementOrderBytes,
//                        contentType = ContentType.Application.Pdf,
//                        status = HttpStatusCode.OK
//                    )
//                } catch (e: Exception) {
//                    application.log.error("Error generating tasks PDF report", e)
//                    call.respond(HttpStatusCode.InternalServerError, "Error generating report: ${e.message}")
//                }
//            }

            post("/placement-order") {
                val payload = call.receive<PlacementOrderPayload>()
                val applicationData = reportsService.prepareApplicationData(payload.data)

                val placementOrderData = PlacementOrderData(
                    firstName = applicationData.firstName,
                    secondName = applicationData.secondName,
                    lastName = applicationData.lastName,
                    faculty = applicationData.education.faculty,
                    course = applicationData.education.course,
                    educationLevel = applicationData.education.educationLevel,
                    dormitory = applicationData.selectedDormitory.split(" ")[1], // take only number
                    room = applicationData.selectedRoom,
                    fluorographyLastDate = applicationData.medicalData.fluorographyLastDate
                )

                val placementOrderBytes = withContext(Dispatchers.IO) {
                    reportService.generatePlacementOrderPdf(placementOrderData)
                }

                call.response.header(
                    HttpHeaders.ContentDisposition,
                    "attachment; filename=\"placement_order.pdf\""
                )
                call.respondBytes(
                    placementOrderBytes,
                    contentType = ContentType.Application.Pdf,
                    status = HttpStatusCode.OK
                )
            }

            // Generate all tasks report in PDF
            get("/tasks/pdf") {
                try {
                    val tasks = repository.allTasks()
                    if (tasks.isEmpty()) {
                        call.respond(HttpStatusCode.NotFound, "No tasks found")
                        return@get
                    }

                    val reportBytes = withContext(Dispatchers.IO) {
                        reportService.generateTasksReportPdf(tasks)
                    }

                    call.response.header(
                        HttpHeaders.ContentDisposition,
                        "attachment; filename=\"tasks_report.pdf\""
                    )
                    call.respondBytes(
                        reportBytes,
                        contentType = ContentType.Application.Pdf,
                        status = HttpStatusCode.OK
                    )
                } catch (e: Exception) {
                    application.log.error("Error generating tasks PDF report", e)
                    call.respond(HttpStatusCode.InternalServerError, "Error generating report: ${e.message}")
                }
            }

            // Generate all tasks report in Excel
            get("/tasks/excel") {
                try {
                    val tasks = repository.allTasks()
                    if (tasks.isEmpty()) {
                        call.respond(HttpStatusCode.NotFound, "No tasks found")
                        return@get
                    }

                    val reportBytes = withContext(Dispatchers.IO) {
                        reportService.generateTasksReportExcel(tasks)
                    }

                    call.response.header(
                        HttpHeaders.ContentDisposition,
                        "attachment; filename=\"tasks_report.xlsx\""
                    )
                    call.respondBytes(
                        reportBytes,
                        contentType = ContentType.parse("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
                        status = HttpStatusCode.OK
                    )
                } catch (e: Exception) {
                    application.log.error("Error generating tasks Excel report", e)
                    call.respond(HttpStatusCode.InternalServerError, "Error generating report: ${e.message}")
                }
            }

            // Generate tasks by priority report in PDF
            get("/tasks/priority/{priority}/pdf") {
                val priorityParam = call.parameters["priority"]
                if (priorityParam == null) {
                    call.respond(HttpStatusCode.BadRequest, "Priority parameter is required")
                    return@get
                }

                try {
                    val priority = Priority.valueOf(priorityParam)
                    val tasks = repository.tasksByPriority(priority)

                    if (tasks.isEmpty()) {
                        call.respond(HttpStatusCode.NotFound, "No tasks found for priority: $priorityParam")
                        return@get
                    }

                    val reportBytes = withContext(Dispatchers.IO) {
                        reportService.generateTasksByPriorityReportPdf(tasks, priority)
                    }

                    call.response.header(
                        HttpHeaders.ContentDisposition,
                        "attachment; filename=\"tasks_${priorityParam.lowercase()}_priority.pdf\""
                    )
                    call.respondBytes(
                        reportBytes,
                        contentType = ContentType.Application.Pdf,
                        status = HttpStatusCode.OK
                    )
                } catch (ex: IllegalArgumentException) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid priority: $priorityParam")
                } catch (e: Exception) {
                    application.log.error("Error generating priority PDF report", e)
                    call.respond(HttpStatusCode.InternalServerError, "Error generating report: ${e.message}")
                }
            }

            // Generate tasks by priority report in Excel
            get("/tasks/priority/{priority}/excel") {
                val priorityParam = call.parameters["priority"]
                if (priorityParam == null) {
                    call.respond(HttpStatusCode.BadRequest, "Priority parameter is required")
                    return@get
                }

                try {
                    val priority = Priority.valueOf(priorityParam)
                    val tasks = repository.tasksByPriority(priority)

                    if (tasks.isEmpty()) {
                        call.respond(HttpStatusCode.NotFound, "No tasks found for priority: $priorityParam")
                        return@get
                    }

                    val reportBytes = withContext(Dispatchers.IO) {
                        reportService.generateTasksByPriorityReportExcel(tasks, priority)
                    }

                    call.response.header(
                        HttpHeaders.ContentDisposition,
                        "attachment; filename=\"tasks_${priorityParam.lowercase()}_priority.xlsx\""
                    )
                    call.respondBytes(
                        reportBytes,
                        contentType = ContentType.parse("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
                        status = HttpStatusCode.OK
                    )
                } catch (ex: IllegalArgumentException) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid priority: $priorityParam")
                } catch (e: Exception) {
                    application.log.error("Error generating priority Excel report", e)
                    call.respond(HttpStatusCode.InternalServerError, "Error generating report: ${e.message}")
                }
            }

            // Get available report endpoints info
            get("/info") {
                val info = mapOf(
                    "available_reports" to listOf(
                        mapOf(
                            "name" to "All Tasks PDF Report",
                            "endpoint" to "/reports/tasks/pdf",
                            "method" to "GET"
                        ),
                        mapOf(
                            "name" to "All Tasks Excel Report",
                            "endpoint" to "/reports/tasks/excel",
                            "method" to "GET"
                        ),
                        mapOf(
                            "name" to "Tasks by Priority PDF Report",
                            "endpoint" to "/reports/tasks/priority/{priority}/pdf",
                            "method" to "GET",
                            "parameters" to mapOf("priority" to "Low, Medium, High, Vital")
                        ),
                        mapOf(
                            "name" to "Tasks by Priority Excel Report",
                            "endpoint" to "/reports/tasks/priority/{priority}/excel",
                            "method" to "GET",
                            "parameters" to mapOf("priority" to "Low, Medium, High, Vital")
                        )
                    )
                )
                call.respond(info)
            }
        }
    }
}