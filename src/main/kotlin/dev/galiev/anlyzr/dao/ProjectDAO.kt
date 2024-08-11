package dev.galiev.anlyzr.dao

import dev.galiev.anlyzr.dto.Project

interface ProjectDAO {
    fun addProject(project: Project): Int
    fun getById(id: Int): List<Project>
}