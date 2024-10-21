package dev.galiev.anlyzr.repository.postgre

import dev.galiev.anlyzr.dto.Stats
import dev.galiev.anlyzr.misc.FAILURE
import dev.galiev.anlyzr.misc.SUCCESS
import dev.galiev.anlyzr.misc.dbQuery
import dev.galiev.anlyzr.model.StatsTable
import dev.galiev.anlyzr.repository.StatsRepository
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset

object PostgresStatsRepository: StatsRepository {
    override suspend fun addStat(stats: Stats): Int = dbQuery {
        val insertResult = StatsTable.insert {
            it[time] = OffsetDateTime.now()
            it[projectId] = stats.projectId
            it[title] = stats.title
            it[downloads] = stats.downloads
            it[followers] = stats.followers
        }
        if (insertResult.insertedCount > 0) SUCCESS else FAILURE
    }

    override suspend fun getInDateRange(id: Int, from: Long, to: Long): List<Stats> = dbQuery {
        StatsTable.selectAll().where {
            StatsTable.time.between(getOffsetTime(from), getOffsetTime(to)) and (StatsTable.projectId eq id)
        }.map(Stats::fromResultRow)
    }

    override suspend fun getStatsByProjectId(id: Int): List<Stats> {
        TODO("Not yet implemented")
    }

    private fun getOffsetTime(value: Long): OffsetDateTime =
        OffsetDateTime.of(LocalDateTime.ofEpochSecond(value, 0, ZoneOffset.UTC), ZoneOffset.UTC)
}