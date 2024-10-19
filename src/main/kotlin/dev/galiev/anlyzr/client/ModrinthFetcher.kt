package dev.galiev.anlyzr.client

import dev.galiev.anlyzr.dto.Project
import dev.galiev.anlyzr.dto.Stats
import dev.galiev.anlyzr.repository.ProjectRepository
import dev.galiev.anlyzr.repository.StatsRepository
import dev.galiev.anlyzr.repository.postgre.PostgresProjectRepository
import dev.galiev.anlyzr.repository.postgre.PostgresStatsRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.datetime.Clock
import kotlinx.serialization.json.Json
import kotlin.time.Duration.Companion.minutes

object ModrinthFetcher {
    const val URL = "https://api.modrinth.com/v2"
    private val projectRep: ProjectRepository = PostgresProjectRepository
    private val statsRep: StatsRepository = PostgresStatsRepository

    private val client: HttpClient = HttpClient(CIO).config {
        install(ContentNegotiation) {
            json(
                contentType = ContentType.Application.Json,
                json = Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    fun fetchData() = runBlocking {
        withTimeout(60.minutes) {
            val response = client.get("$URL/user/GalievDev/projects")
            response.body<List<Project>>().forEach { project ->
                if (!projectRep.exists(project.title)) {
                    projectRep.add(project)
                }
                val projectModel = projectRep.getAll().find { it.title == project.title }!!
                statsRep.addStat(Stats(
                    Clock.System.now().toEpochMilliseconds(),
                    projectModel.projectId,
                    projectModel.title,
                    project.downloads,
                    project.followers))
            }
        }
/*        val timer = Timer()
        val task = object : TimerTask() {
            override fun run() {
                runBlocking {
                    try {

                    } catch (e: Exception) {
                        println("Error fetching API response: ${e.message}")
                    }
                }
            }
        }
        timer.scheduleAtFixedRate(task, 0, 3600000)*/
    }
}