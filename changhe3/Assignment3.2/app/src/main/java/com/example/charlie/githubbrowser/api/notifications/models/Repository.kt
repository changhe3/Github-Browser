package com.example.charlie.githubbrowser.api.notifications.models

import com.squareup.moshi.Json
import java.net.URL

data class Repository(@Json(name = "full_name") val fullName: String,
                      @Json(name = "html_url") val url: String)