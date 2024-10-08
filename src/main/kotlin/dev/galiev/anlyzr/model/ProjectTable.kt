package dev.galiev.anlyzr.model

import org.jetbrains.exposed.dao.id.IntIdTable

object ProjectTable: IntIdTable("projects") {
    val title = varchar("title", 255)
    val description = text("description")
}