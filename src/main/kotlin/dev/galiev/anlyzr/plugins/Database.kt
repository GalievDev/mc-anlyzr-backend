package dev.galiev.anlyzr.plugins

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

fun configureDatabase() {
    Database.connect(HikariDataSource(HikariConfig().apply {
        driverClassName = "org.postgresql.Driver"
        jdbcUrl = "jdbc:postgresql://$POSTGRES_HOST:$POSTGRES_PORT/$POSTGRES_DB"
        username = POSTGRES_USER
        password = POSTGRES_PASS
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        validate()
    }))
}