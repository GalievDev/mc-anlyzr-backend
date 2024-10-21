package dev.galiev.anlyzr.plugins

import dev.galiev.anlyzr.repository.postgre.PostgresStatsRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.datetime.Clock

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respond("Hello World!")
        }
        route("/stats/{id}") {
            get("/line") {
                val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(
                    HttpStatusCode.BadRequest, "Bad Request"
                )

                val from = call.parameters["from"]?.toLongOrNull()
                val to = call.parameters["to"]?.toLongOrNull() ?: Clock.System.now().toEpochMilliseconds()

                call.respond(PostgresStatsRepository.getInDateRange(id, from!!, to))
            }
            get("/pie") {
                val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(
                    HttpStatusCode.BadRequest, "Bad Request"
                )

                call.respond(PostgresStatsRepository.getStatsByProjectId(id))
            }
        }
    }
}
