package dev.galiev.anlyzr.plugins

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dev.galiev.anlyzr.model.ProjectTable
import dev.galiev.anlyzr.model.StatsTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun configureDatabase() {
    Database.connect(HikariDataSource(HikariConfig().apply {
        driverClassName = "org.postgresql.Driver"
        jdbcUrl = "jdbc:postgresql://$POSTGRES_HOST:$POSTGRES_PORT/$POSTGRES_DB"
        username = POSTGRES_USER
        password = POSTGRES_PASS
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        validate()
    }))

    transaction {
            SchemaUtils.create(ProjectTable)
            SchemaUtils.create(StatsTable)
        }
}