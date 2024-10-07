package dev.galiev.anlyzr.repository.postgre

import dev.galiev.anlyzr.dto.Project
import dev.galiev.anlyzr.misc.FAILURE
import dev.galiev.anlyzr.misc.SUCCESS
import dev.galiev.anlyzr.misc.dbQuery
import dev.galiev.anlyzr.model.ProjectModel
import dev.galiev.anlyzr.repository.ProjectRepository
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

object PostgresProjectRepository: ProjectRepository {
    override suspend fun add(project: Project): Int = dbQuery {
        val insertResult = ProjectModel.insert {
            it[title] = project.title
            it[description] = project.description
        }
        if (insertResult.insertedCount > 0) SUCCESS else FAILURE
    }

    override suspend fun getAll(): List<Project> = dbQuery {
        ProjectModel.selectAll().map(Project::fromResultRow)
    }

    override suspend fun getById(id: Int): Project? = dbQuery {
        ProjectModel.selectAll().where{ ProjectModel.id eq id }
            .map(Project::fromResultRow).singleOrNull()
    }
}