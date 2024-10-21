package dev.galiev.anlyzr.dto

import dev.galiev.anlyzr.model.StatsTable
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ResultRow

@Serializable
data class Stats (
    val time: Long,
    val projectId: Int = 0,
    val title: String = "",
    val downloads: Int = 0,
    val followers: Int = 0
) {
    companion object {
        fun fromResultRow(resultRow: ResultRow): Stats = Stats(
            time = resultRow[StatsTable.time].toEpochSecond(),
            projectId = resultRow[StatsTable.projectId],
            title = resultRow[StatsTable.title],
            downloads = resultRow[StatsTable.downloads],
            followers = resultRow[StatsTable.followers]
        )
    }
}