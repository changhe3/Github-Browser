package com.example.charlie.githubbrowser.api.notifications.models

import com.squareup.moshi.Json
import java.util.*


data class Notification(
        val reason: String,
        @Json(name = "updated_at") val updatedAt: Date,
        val subject: Subject,
        val repository: Repository
        )