package dev.galiev.anlyzr.dto

import dev.galiev.anlyzr.model.ProjectTable
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ResultRow

@Serializable
data class Project(
    var projectId: Int = -1,
    val title: String = "",
    val description: String = "",
    val downloads: Int = 0,
    val followers: Int = 0
) {
    companion object {
        fun fromResultRow(resultRow: ResultRow): Project = Project(
            projectId = resultRow[ProjectTable.id].value,
            title = resultRow[ProjectTable.title],
            description = resultRow[ProjectTable.description],
        )
    }
}