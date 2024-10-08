package dev.galiev.anlyzr.plugins

import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        val converter = KotlinxSerializationConverter( Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        })
        register(ContentType.Application.Json, converter)
    }
}
