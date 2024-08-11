package dev.galiev.anlyzr.dao.impl

import dev.galiev.anlyzr.dao.StatsDAO
import dev.galiev.anlyzr.dto.Project
import dev.galiev.anlyzr.plugins.Database.update
import java.sql.Timestamp
import java.time.Instant

class StatsDAOImpl: StatsDAO {

    override fun addStat(project: Project): Int = update("INSERT INTO projects_stats(time, project_id, title, downloads, followers) " +
            "VALUES('${Timestamp.from(Instant.now())}', '${project.projectId}', '${project.title}', '${project.downloads}', '${project.followers}')")


    override fun getInDateRange(id: Int, start: Int, end: Int): List<Project> {
        TODO("Not yet implemented")
    }

    override fun getStatsByProjectId(id: Int): List<Project> {
        TODO("Not yet implemented")
    }
}