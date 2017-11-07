package com.example.charlie.githubbrowser.api.apollo.adapters

import com.apollographql.apollo.CustomTypeAdapter
import java.net.URL

object URLAdapter : CustomTypeAdapter<URL> {

    override fun encode(value: URL): String = value.toString()

    override fun decode(value: String): URL = URL(value)
}