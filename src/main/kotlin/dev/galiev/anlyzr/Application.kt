package dev.galiev.anlyzr

import dev.galiev.anlyzr.client.ModrinthFetcher
import dev.galiev.anlyzr.plugins.configureDatabase
import dev.galiev.anlyzr.plugins.configureHTTP
import dev.galiev.anlyzr.plugins.configureRouting
import dev.galiev.anlyzr.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    ModrinthFetcher.fetchData()
    configureHTTP()
    configureSerialization()
    configureRouting()
    configureDatabase()
}
