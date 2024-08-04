package dev.galiev.anlyzr.client

import dev.galiev.anlyzr.dto.Project
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object ModrinthFetcher {
    const val URL = "https://api.modrinth.com/v2"
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


    suspend fun getProjects(): List<Project> {
        val response: HttpResponse = client.get("$URL/user/GalievDev/projects") {
            contentType(ContentType.Application.Json)
        }

        val projects: List<Project> = response.body()
        return projects
    }
}