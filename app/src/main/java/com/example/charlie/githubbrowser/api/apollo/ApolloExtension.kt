package com.example.charlie.githubbrowser.api.apollo

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import kotlin.coroutines.experimental.suspendCoroutine

suspend fun <T> ApolloCall<T>.awaitResult(): Result<T> = suspendCoroutine { c ->
    this.enqueue(object : ApolloCall.Callback<T>() {
        override fun onResponse(response: Response<T>) {
            if (!response.hasErrors()) c.resume(Result.Ok(response.data()!!))
            else c.resume(Result.Error(response.data(), response.errors()))
        }

        override fun onFailure(e: ApolloException) {
            c.resumeWithException(e)
        }
    })
}

