package com.example.charlie.githubbrowser.ui

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import com.example.charlie.githubbrowser.R
import com.example.charlie.githubbrowser.api.apollo.Result
import fragment.PageInfoQuery
import fragment.UserListItemQuery
import kotlinx.android.synthetic.main.activity_user_list.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.toast
import util.android.EndlessRecyclerViewScrollListener


abstract class UserListActivity : Activity() {

    protected var cursor: String? = null
    protected var hasNextPage = true
    protected val maxItemPerRequest = 30

    protected val data = mutableListOf<UserListItemQuery>()
    protected val adapter by lazy { UserListAdapter(data, { viewer ->
        MainActivity.goToPage(this, viewer)
    }) }
    private lateinit var listener: EndlessRecyclerViewScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        val linearLayoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        user_list.layoutManager = linearLayoutManager
        user_list.adapter = adapter
        listener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                if (!hasNextPage) return
                loadMoreData()
            }
        }
        user_list.addOnScrollListener(listener)
        loadMoreData()
    }

    abstract suspend protected fun fetchData(): Result<Pair<List<UserListItemQuery>, PageInfoQuery>>

    private fun loadMoreData() {
        async(UI) {
            val result = fetchData()
            when (result) {
                is Result.Ok -> {
                    Log.d("Network", "Data Retrieved")
                    cursor = result.data.second.endCursor()
                    hasNextPage = result.data.second.hasNextPage()
                    val list = result.data.first
                    if (list.isNotEmpty()) {
                        val start = data.size
                        data.addAll(list)
                        adapter.notifyItemRangeInserted(start, list.size)
                    }
                }
                is Result.Error -> {
                    toast(result.errorMessage)
                }
            }
        }
    }

    private fun resetData() {
        cursor = null
        hasNextPage = true

        data.clear()
        adapter.notifyDataSetChanged()
        listener.resetState()
        loadMoreData()
    }
}