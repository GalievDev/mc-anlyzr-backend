package dev.galiev.anlyzr.dao

import dev.galiev.anlyzr.dto.Project

interface StatsDAO {
    fun addStat(project: Project): Int
    fun getInDateRange(id: Int, start: Int, end: Int): List<Project>
    fun getStatsByProjectId(id: Int): List<Project>
}