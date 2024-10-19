package dev.galiev.anlyzr.repository

import dev.galiev.anlyzr.dto.Stats

interface StatsRepository {
    suspend fun addStat(stats: Stats): Int
    suspend fun getInDateRange(id: Int, start: Long, end: Long): List<Stats>
    suspend fun getStatsByProjectId(id: Int): List<Stats>
}