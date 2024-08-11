package dev.galiev.anlyzr.dao.impl

import dev.galiev.anlyzr.dao.ProjectDAO
import dev.galiev.anlyzr.dto.Project
import dev.galiev.anlyzr.plugins.Database.query

class ProjectDAOImpl: ProjectDAO {

    override fun addOrUpdate(project: Project): Int {
        var returnId = 0
        query("SELECT project_id FROM projects WHERE title = '${project.title}'") { checkResultSet ->
            if (checkResultSet.next()) {
                 returnId = update(project)
            } else {
                returnId = add(project)
            }
            returnId = checkResultSet.getInt("project_id")
        }
        return returnId
    }

    private fun add(project: Project): Int {
        var returnId = 0
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

        return returnId
    }

    private fun update(project: Project): Int {
        var returnId = 0
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
        return returnId
    }

    override fun getById(id: Int): Project? {
        var project: Project? = null
        query("SELECT project_id FROM projects WHERE id IN '${id}' LIMIT 1") { resultSet ->
            if (resultSet.next()) {
                project = Project(
                    resultSet.getInt("project_id"),
                    resultSet.getString("title"),
                    resultSet.getInt("downloads"),
                    resultSet.getInt("followers")
                )
            }
        }
        return project
    }
}