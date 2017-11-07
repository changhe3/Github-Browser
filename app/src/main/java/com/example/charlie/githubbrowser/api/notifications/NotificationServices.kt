package com.example.charlie.githubbrowser.api.notifications

import com.example.charlie.githubbrowser.api.notifications.models.Notification
import com.squareup.moshi.Json
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query


interface NotificationServices {

    data class MarkAsReadBody(@Json(name = "last_read_at") val lastReadAt: String)

    @GET("/notifications")
    fun getNotifications(): Call<List<Notification>>

    @PUT("/notifications")
    fun markAsRead(@Body body: MarkAsReadBody): Call<Void>
}