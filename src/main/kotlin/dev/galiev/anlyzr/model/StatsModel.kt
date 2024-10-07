package dev.galiev.anlyzr.model

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.timestampWithTimeZone

object StatsModel: Table("stats") {
    val time = timestampWithTimeZone("time")
    val projectId = integer("project_id").references(ProjectModel.id, ReferenceOption.CASCADE, ReferenceOption.CASCADE)
    val title = varchar("title", 255)
    val downloads = integer("downloads")
    val followers = integer("followers")
}