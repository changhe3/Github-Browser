package com.example.charlie.githubbrowser.api.oauth

import com.example.charlie.githubbrowser.api.API
import com.squareup.moshi.Json
import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface OAuth {

    data class AccessToken(@Json(name = "access_token") val token: String,
                           @Json(name = "token_type") val type: String)

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    fun getToken(
            @Query("code") code: String,
            @Query("state") state: String,
            @Query("client_id") clientId: String = API.clientID,
            @Query("client_secret") clientSecret: String = API.clientSecret
    ): Call<AccessToken>
}