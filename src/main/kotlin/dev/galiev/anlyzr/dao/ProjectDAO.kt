package dev.galiev.anlyzr.dao

import dev.galiev.anlyzr.dto.Project

interface ProjectDAO {
    fun addOrUpdate(project: Project): Int
    fun getById(id: Int): Project?
}