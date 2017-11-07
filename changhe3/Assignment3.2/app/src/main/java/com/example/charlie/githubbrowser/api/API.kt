package com.example.charlie.githubbrowser.api

import android.app.Activity
import com.apollographql.apollo.ApolloClient
import com.example.charlie.githubbrowser.api.apollo.adapters.URLAdapter
import com.example.charlie.githubbrowser.api.notifications.NotificationServices
import com.example.charlie.githubbrowser.api.oauth.OAuth
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import type.CustomType
import java.net.URL
import java.util.*
import kotlin.properties.Delegates

object API {

    fun Activity.initApp() {
        Realm.init(applicationContext)
    }

    val clientID = "b3c297a935b3ce0c9e9e"
    val clientSecret = "d8481011a96092b004e7bfb6b4f987f5b6f1747a"
    val callbackURL = "https://github-browser-5d27c.firebaseapp.com/__/auth/handler"

    val apiToken = "cd5b28443470db326798761e9135582f01534e6c"
    private val endpoint = "https://api.github.com/graphql"

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://github.com/")
            .addConverterFactory(MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )).build()
    val oAuth = retrofit.create(OAuth::class.java)

    private lateinit var token: OAuth.AccessToken
    lateinit var okHttpClient: OkHttpClient
    lateinit var apolloClient: ApolloClient
    lateinit var restClient: Retrofit
    lateinit var notificationServices: NotificationServices

    fun initAPI(receivedToken: OAuth.AccessToken) {
        token = receivedToken
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor { chain ->
                    val request = chain.request()
                    val newRequest = request
                            .newBuilder()
                            .addHeader("Authorization", "${token.type} ${token.token}")
                            .build()
                    chain.proceed(newRequest)
                }.build()
        apolloClient = ApolloClient.builder()
                .serverUrl(endpoint)
                .okHttpClient(okHttpClient)
                .addCustomTypeAdapter(CustomType.URI, URLAdapter)
                .build()
        restClient = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(
                        Moshi.Builder()
                                .add(KotlinJsonAdapterFactory())
                                .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                                .build()
                )).build()
        notificationServices = restClient.create(NotificationServices::class.java)
    }

}