package dev.galiev.anlyzr.repository

import dev.galiev.anlyzr.dto.Project

interface ProjectRepository {
    suspend fun add(project: Project): Int
    suspend fun getAll(): List<Project>
    suspend fun getById(id: Int): Project?
}