package com.example.service

import com.example.app.models.PlacementOrderData
import com.example.model.Task
import com.example.model.Priority
import net.sf.jasperreports.engine.*
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import net.sf.jasperreports.engine.export.JRPdfExporter
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter
import net.sf.jasperreports.export.SimpleExporterInput
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput
import net.sf.jasperreports.export.SimplePdfExporterConfiguration
import net.sf.jasperreports.export.SimpleXlsxExporterConfiguration
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class JasperReportService {

    companion object {
        private const val TASKS_REPORT_PATH = "/reports/tasks_report.jrxml"
        private const val TASKS_BY_PRIORITY_REPORT_PATH = "/reports/tasks_by_priority_report.jrxml"
    }

    // Precompiled templates (loaded once)
    private val tasksReport: JasperReport by lazy { compileReport(TASKS_REPORT_PATH) }
    private val tasksByPriorityReport: JasperReport by lazy { compileReport(TASKS_BY_PRIORITY_REPORT_PATH) }
    private val placementOrder: JasperReport by lazy { compileReport("/reports/placement-order.jrxml") }

    /**
     * Compile a jrxml file into a JasperReport once at startup.
     */
    private fun compileReport(path: String): JasperReport {
        val reportStream: InputStream = this::class.java.getResourceAsStream(path)
            ?: throw IllegalArgumentException("Report template not found: $path")
        return JasperCompileManager.compileReport(reportStream)
    }

    suspend fun generatePlacementOrderPdf(placementOrderData: PlacementOrderData) =
        generatePlacementOrder(placementOrder, placementOrderData)

    suspend fun generateTasksReportPdf(tasks: List<Task>) =
        generateReport(tasksReport, tasks, "pdf")

    suspend fun generateTasksReportExcel(tasks: List<Task>) =
        generateReport(tasksReport, tasks, "xlsx")

    suspend fun generateTasksByPriorityReportPdf(tasks: List<Task>, priority: Priority) =
        generateReport(
            tasksByPriorityReport,
            tasks,
            "pdf",
            mapOf(
                "PRIORITY_FILTER" to priority.name,
                "REPORT_TITLE" to "Tasks Report - ${priority.name} Priority",
                "GENERATED_DATE" to LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            )
        )

    suspend fun generateTasksByPriorityReportExcel(tasks: List<Task>, priority: Priority) =
        generateReport(
            tasksByPriorityReport,
            tasks,
            "xlsx",
            mapOf(
                "PRIORITY_FILTER" to priority.name,
                "REPORT_TITLE" to "Tasks Report - ${priority.name} Priority",
                "GENERATED_DATE" to LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            )
        )

    private fun generateReport(
        jasperReport: JasperReport,
        tasks: List<Task>,
        format: String,
        parameters: Map<String, Any> = emptyMap()
    ): ByteArray {
        return try {
            val dataSource = JRBeanCollectionDataSource(tasks.map { task ->
                TaskReportData(
                    name = task.name,
                    description = task.description,
                    priority = task.priority.name,
                    priorityLevel = when (task.priority) {
                        Priority.Vital -> 4
                        Priority.High -> 3
                        Priority.Medium -> 2
                        Priority.Low -> 1
                    }
                )
            })

            val reportParameters = parameters.toMutableMap().apply {
                put("REPORT_TITLE", parameters["REPORT_TITLE"] ?: "Tasks Report")
                put("GENERATED_DATE", parameters["GENERATED_DATE"]
                    ?: LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                put("TOTAL_TASKS", tasks.size)
            }

            val jasperPrint = JasperFillManager.fillReport(jasperReport, reportParameters, dataSource)

            when (format.lowercase()) {
                "pdf" -> exportToPdf(jasperPrint)
                "xlsx" -> exportToExcel(jasperPrint)
                else -> throw IllegalArgumentException("Unsupported format: $format")
            }
        } catch (e: Exception) {
            throw RuntimeException("Error generating report", e)
        }
    }

    private fun generatePlacementOrder(
        jasperReport: JasperReport,
        placementOrderData: PlacementOrderData,
    ): ByteArray {
        return try {
            val parameters = mapOf(
                "firstName" to placementOrderData.firstName,
                "secondName" to placementOrderData.secondName,
                "lastName" to "Placement Order",
                "faculty" to placementOrderData.faculty,
                "course" to placementOrderData.course,
                "educationLevel" to placementOrderData.educationLevel,
                "dormitory" to placementOrderData.dormitory,
                "room" to placementOrderData.room,
            )

            val jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, JREmptyDataSource(1))

            exportToPdf(jasperPrint)
        } catch (e: Exception) {
            throw RuntimeException("Error generating report", e)
        }
    }

    private fun exportToPdf(jasperPrint: JasperPrint): ByteArray {
        val outputStream = ByteArrayOutputStream()
        val pdfExporter = JRPdfExporter()
        pdfExporter.setExporterInput(SimpleExporterInput(jasperPrint))
        pdfExporter.setExporterOutput(SimpleOutputStreamExporterOutput(outputStream))
        pdfExporter.setConfiguration(SimplePdfExporterConfiguration())
        pdfExporter.exportReport()
        return outputStream.toByteArray()
    }

    private fun exportToExcel(jasperPrint: JasperPrint): ByteArray {
        val outputStream = ByteArrayOutputStream()
        val xlsxExporter = JRXlsxExporter()
        xlsxExporter.setExporterInput(SimpleExporterInput(jasperPrint))
        xlsxExporter.setExporterOutput(SimpleOutputStreamExporterOutput(outputStream))
        xlsxExporter.setConfiguration(SimpleXlsxExporterConfiguration())
        xlsxExporter.exportReport()
        return outputStream.toByteArray()
    }
}

data class TaskReportData(
    val name: String,
    val description: String,
    val priority: String,
    val priorityLevel: Int
)
