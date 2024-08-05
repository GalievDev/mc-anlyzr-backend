package dev.galiev.anlyzr.dao

import dev.galiev.anlyzr.dto.Project

interface ProjectDAO {
    fun add(project: Project): Int
    fun getById(id: Int): List<Project>
    fun getInDateRange(id: Int, start: Int, end: Int): Project?
}