package com.example.charlie.githubbrowser.ui

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import com.db.chart.model.Bar
import com.db.chart.model.BarSet
import com.example.charlie.githubbrowser.R
import com.example.charlie.githubbrowser.api.API
import com.example.charlie.githubbrowser.api.apollo.Result
import com.example.charlie.githubbrowser.api.apollo.awaitResult
import kotlinx.android.synthetic.main.activity_language.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.toast

class LanguageActivity : Activity() {

    private val id: String by lazy {
        intent.extras.getString(getString(R.string.intent_language_activity_id))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        async(UI) {
            val query = LanguageQuery.builder().repoId(id).build()
            val result = async(CommonPool) { API.apolloClient.query(query).awaitResult() }.await()
            when (result) {
                is Result.Ok -> {
                    val languages = result.data.node()?.asRepository()!!.languages()!!
                    val data = languages.edges().orEmpty().map {
                        Bar(it.node().name(), it.size().toFloat()).apply {
                            color = Color.parseColor(it.node().color() ?: "#000000")
                        }
                    }.toMutableList()
                    val extra = languages.totalSize() - data.fold(0.0) { acc, elem -> acc + elem.value }
                    data.add(Bar("Others", extra.toFloat()))
                    bar_chart.addData(BarSet().apply {
                        for (bar in data) {
                            addBar(bar)
                        }
                    })
                    bar_chart.show()
                }
                is Result.Error -> {
                    Log.e("error", result.errorMessage)
                }
            }
        }
    }
}
