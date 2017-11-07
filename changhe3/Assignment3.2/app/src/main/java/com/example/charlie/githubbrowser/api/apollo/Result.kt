package com.example.charlie.githubbrowser.api.apollo

sealed class Result<T> {

    data class Ok<T>(val data: T) : Result<T>()

    data class Error<T>(val data: T?, val errors: List<com.apollographql.apollo.api.Error>) : Result<T>() {

        val errorMessage
            get() = errors.joinToString("\n") { err ->
                err.locations().joinTo(StringBuilder()) { loc ->
                    "${loc.line()}:${loc.column()} "
                }.append(err.message())
            }
    }

    inline fun <Q> map(f: (T) -> Q): Result<Q> = when (this) {
        is Ok -> Ok(f(data))
        is Error -> Error(null, errors)
    }
}