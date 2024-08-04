package dev.galiev.anlyzr.client

import dev.galiev.anlyzr.dto.Project
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout
import kotlinx.serialization.json.Json
import kotlin.time.Duration.Companion.milliseconds

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

    suspend fun fetchData(): List<Project> {
        var count = 0
        withTimeout(5000.milliseconds) {
            while (true) {
                count++
                client.get("$URL/user/GalievDev/projects") {
                    contentType(ContentType.Application.Json)
                }
                delay(1000.milliseconds)
            }
        }
    }
}