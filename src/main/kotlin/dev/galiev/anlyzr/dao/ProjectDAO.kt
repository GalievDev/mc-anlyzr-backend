package dev.galiev.anlyzr.dao

import dev.galiev.anlyzr.dto.Project

interface ProjectDAO {
    fun add(project: Project): Int
    fun get(id: Int): Project?
    fun getAll(): List<Project>
}