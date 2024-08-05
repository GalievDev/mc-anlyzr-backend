package dev.galiev.anlyzr.dao.impl

import dev.galiev.anlyzr.dao.ProjectDAO
import dev.galiev.anlyzr.dto.Project
import dev.galiev.anlyzr.plugins.Database.update
import java.sql.Timestamp
import java.time.Instant

object ProjectDAOImpl: ProjectDAO {

    override fun add(project: Project): Int = update("INSERT INTO projects(time, title, downloads, followers) " +
                "VALUES('${Timestamp.from(Instant.now())}', '${project.title}', '${project.downloads}', '${project.followers}')")

    override fun getById(id: Int): List<Project> {
        TODO("Not yet implemented")
    }

    override fun getInDateRange(id: Int, start: Int, end: Int): Project? {
        TODO("Not yet implemented")
    }
}