package dev.galiev.anlyzr.dao.impl

import dev.galiev.anlyzr.dao.ProjectDAO
import dev.galiev.anlyzr.dto.Project
import dev.galiev.anlyzr.plugins.Database.query
import dev.galiev.anlyzr.plugins.Database.update
import java.sql.Timestamp
import java.time.Instant

object ProjectDAOImpl: ProjectDAO {

    override fun addStat(project: Project): Int = update("INSERT INTO projects_stats(time, project_id, title, downloads, followers) " +
            "VALUES('${Timestamp.from(Instant.now())}', '${project.projectId}', '${project.title}', '${project.downloads}', '${project.followers}')")

    override fun addProject(project: Project): Int {
        var returnId = -1
        query("SELECT project_id FROM projects WHERE title = '${project.title}'") { checkResultSet ->
            if (checkResultSet.next()) {
                val updateQuery = """
                        UPDATE projects
                        SET downloads = ${project.downloads},
                            followers = ${project.followers}
                        WHERE project_id = ${project.projectId}
                            AND (downloads <> ${project.downloads} OR followers <> ${project.followers})
                        RETURNING PROJECT_ID
                    """.trimMargin()

                query(updateQuery) { updateResult ->
                    if (updateResult.next()) {
                        returnId = updateResult.getInt("project_id")
                    }
                }
            } else {
                val insertQuery = """
                        INSERT INTO projects(title, downloads, followers)
                        VALUES ('${project.title}', '${project.downloads}', '${project.followers}')
                        RETURNING PROJECT_ID
                    """.trimMargin()

                query(insertQuery) { insertResult ->
                    if (insertResult.next()) {
                        returnId = insertResult.getInt("project_id")
                    }
                }
            }
            returnId = checkResultSet.getInt("project_id")
        }
        return returnId
    }

    override fun getById(id: Int): List<Project> {
        TODO("Not yet implemented")
    }

    override fun getInDateRange(id: Int, start: Int, end: Int): Project? {
        TODO("Not yet implemented")
    }
}