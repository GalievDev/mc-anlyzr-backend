package dev.galiev.anlyzr.client

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import java.util.*

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

    fun fetchData() {
        val timer = Timer()
        val task = object : TimerTask() {
            override fun run() {
                runBlocking {
                    try {
                        client.get("$URL/user/GalievDev/projects") {
                            contentType(ContentType.Application.Json)
                        }
                    } catch (e: Exception) {
                        println("Error fetching API response: ${e.message}")
                    }
                }
            }
        }
        timer.scheduleAtFixedRate(task, 0, 3600000)
    }
}