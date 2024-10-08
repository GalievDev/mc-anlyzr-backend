package dev.galiev.anlyzr.repository.postgre

import dev.galiev.anlyzr.dto.Project
import dev.galiev.anlyzr.misc.FAILURE
import dev.galiev.anlyzr.misc.SUCCESS
import dev.galiev.anlyzr.misc.dbQuery
import dev.galiev.anlyzr.model.ProjectTable
import dev.galiev.anlyzr.repository.ProjectRepository
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

object PostgresProjectRepository: ProjectRepository {
    override suspend fun add(project: Project): Int = dbQuery {
        val insertResult = ProjectTable.insert {
            it[title] = project.title
            it[description] = project.description
        }
        if (insertResult.insertedCount > 0) SUCCESS else FAILURE
    }

    override suspend fun getAll(): List<Project> = dbQuery {
        ProjectTable.selectAll().map(Project::fromResultRow)
    }

    override suspend fun getById(id: Int): Project? = dbQuery {
        ProjectTable.selectAll().where{ ProjectTable.id eq id }
            .map(Project::fromResultRow).singleOrNull()
    }

    override suspend fun exists(title: String): Boolean = dbQuery {
        !ProjectTable.selectAll().where{ ProjectTable.title eq title }.empty()
    }
}