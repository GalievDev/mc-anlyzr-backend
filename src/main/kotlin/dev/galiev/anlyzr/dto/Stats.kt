package dev.galiev.anlyzr.dto

import kotlinx.serialization.Serializable

@Serializable
data class Stats (
    val projectId: Int = 0,
    val title: String = "",
    val downloads: Int = 0,
    val followers: Int = 0
)