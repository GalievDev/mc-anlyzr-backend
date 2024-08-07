package dev.galiev.anlyzr.dto

import kotlinx.serialization.Serializable

@Serializable
data class Project(
    var projectId: Int = -1,
    val title: String = "",
    val downloads: Int = 0,
    val followers: Int = 0,
)