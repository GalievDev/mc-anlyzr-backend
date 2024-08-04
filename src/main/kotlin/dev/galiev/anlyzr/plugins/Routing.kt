package dev.galiev.anlyzr.plugins

import dev.galiev.anlyzr.client.ModrinthFetcher
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respond(ModrinthFetcher.getProjects())
        }
    }
}
