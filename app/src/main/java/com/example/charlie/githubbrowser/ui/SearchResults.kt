package com.example.charlie.githubbrowser.ui

import SearchQuery
import android.app.Activity
import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.example.charlie.githubbrowser.R
import com.example.charlie.githubbrowser.api.API
import com.example.charlie.githubbrowser.api.apollo.Result
import com.example.charlie.githubbrowser.api.apollo.awaitResult
import kotlinx.android.synthetic.main.activity_search_results.*
import kotlinx.android.synthetic.main.activity_user_list.view.*
import kotlinx.android.synthetic.main.generic_recyclerview_layout.view.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.toast
import type.SearchType
import util.android.EndlessRecyclerViewScrollListener
import util.android.OnTabSelectedAdapter
import util.android.ViewPagerAdapter

class SearchResults : Activity() {

    data class CursorObject(var hasNextPage: Boolean, var endCursor: String?) {
        constructor() : this(true, null)
    }

    private val maxItemPerRequest = 30

    private val viewer: String by lazy {
        intent.getBundleExtra(SearchManager.APP_DATA)[getString(R.string.intent_repo_list_viewer)] as String
    }

    private val repoListCursor = CursorObject()

    private val userListCursor = CursorObject()

    private val userData = mutableListOf<SearchQuery.Node>()
    private val repoData = mutableListOf<SearchQuery.Node>()
    private val userAdapter by lazy {
        SearchResultsListAdapter(userData, viewer, {MainActivity.goToPage(this, it)}, SearchType.USER)
    }
    private val repoAdapter by lazy {
        SearchResultsListAdapter(repoData, viewer, {MainActivity.goToPage(this, it)}, SearchType.REPOSITORY)
    }

    private val queryString by lazy {
        assert(intent.action == Intent.ACTION_SEARCH, {"This activity must be initialized by a search!"})
        intent.getStringExtra(SearchManager.QUERY)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)

        search_results_pager.adapter = ViewPagerAdapter({loadUsers(it)}, {loadRepos(it)})
    }

    suspend fun fetchData(searchType: SearchType, cursor: CursorObject): Result<SearchQuery.Data> {
        val query = SearchQuery.builder().str(queryString).first(maxItemPerRequest).after(cursor.endCursor).type(searchType).build()
        return async<Result<SearchQuery.Data>>(CommonPool) { API.apolloClient.query(query).awaitResult() }.await()
    }

    private fun loadData(searchType: SearchType, cursor: CursorObject, list: MutableList<SearchQuery.Node>, adapter: RecyclerView.Adapter<*>) {
        async(UI) {
            val result = fetchData(searchType, cursor)
            when (result) {
                is Result.Ok -> {
                    val search = result.data.search()
                    cursor.apply {
                        val pageInfoQuery = search.pageInfo().fragments().pageInfoQuery()
                        hasNextPage = pageInfoQuery.hasNextPage()
                        endCursor = pageInfoQuery.endCursor()
                    }

                    val newData = search.nodes().orEmpty().filter { it.__typename() == searchType.name.toLowerCase().capitalize() }
                    if (newData.isNotEmpty()) {
                        val start = newData.size
                        list.addAll(newData)
                        adapter.notifyItemRangeInserted(start, newData.size)
                    }
                }
                is Result.Error -> {
                    toast(result.errorMessage)
                }
            }
        }
    }

    private fun loadRepos(view: View): View = view.apply {
        val linearLayoutManager = LinearLayoutManager(this@SearchResults, LinearLayout.VERTICAL, false)
        recycler_view.layoutManager = linearLayoutManager
        recycler_view.adapter = repoAdapter
        recycler_view.addOnScrollListener(object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                if (!repoListCursor.hasNextPage) return
                loadData(SearchType.REPOSITORY, repoListCursor, repoData, repoAdapter)
            }
        })
        loadData(SearchType.REPOSITORY, repoListCursor, repoData, repoAdapter)
    }

    private fun loadUsers(view: View): View = view.apply {
        val linearLayoutManager = LinearLayoutManager(this@SearchResults, LinearLayout.VERTICAL, false)
        recycler_view.layoutManager = linearLayoutManager
        recycler_view.adapter = userAdapter
        recycler_view.addOnScrollListener(object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                if (!userListCursor.hasNextPage) return
                loadData(SearchType.USER, userListCursor, userData, userAdapter)
            }
        })
        loadData(SearchType.USER, userListCursor, userData, userAdapter)
    }
}
