package dev.galiev.anlyzr.dto

import dev.galiev.anlyzr.model.ProjectModel
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ResultRow

@Serializable
data class Project(
    var projectId: Int = -1,
    val title: String = "",
    val description: String = "",
) {
    companion object {
        fun fromResultRow(resultRow: ResultRow): Project = Project(
            projectId = resultRow[ProjectModel.id].value,
            title = resultRow[ProjectModel.title],
            description = resultRow[ProjectModel.description],
        )
    }
}