package com.example.charlie.githubbrowser.ui

import ReposQuery
import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import com.example.charlie.githubbrowser.R
import com.example.charlie.githubbrowser.api.API
import com.example.charlie.githubbrowser.api.apollo.Result
import com.example.charlie.githubbrowser.api.apollo.awaitResult
import com.example.charlie.githubbrowser.models.apollo.Repository
import com.example.charlie.githubbrowser.models.realm.Repository as RealmRepo
import com.example.charlie.githubbrowser.models.realm.User
import com.example.charlie.githubbrowser.models.realm.updateRealm
import com.vicpin.krealmextensions.queryFirst
import com.vicpin.krealmextensions.save
import fragment.RepoItemQuery
import kotlinx.android.synthetic.main.activity_repo_list.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.toast
import type.OrderDirection
import type.RepositoryOrder
import type.RepositoryOrderField
import util.android.EndlessRecyclerViewScrollListener

class RepoListActivity : Activity() {

    enum class RepositoryOrderFieldDescriptiveWrapper(val value: RepositoryOrderField,
                                                      val description: String) {

        CREATED_AT(RepositoryOrderField.CREATED_AT, "Order by creation time"),
        UPDATED_AT(RepositoryOrderField.UPDATED_AT, "Order by update time"),
        PUSH_AT(RepositoryOrderField.PUSHED_AT, "Order by push time"),
        NAME(RepositoryOrderField.NAME, "Order by name"),
        STARS(RepositoryOrderField.STARGAZERS, "Order by number of stars");

        override fun toString(): String = description
    }

    enum class OrderDirectionDescriptiveWrapper(val value: OrderDirection, val description: String) {
        ASC(OrderDirection.ASC, "ASC"),
        DESC(OrderDirection.DESC, "DESC");

        override fun toString(): String = description
    }

    private val maxItemPerRequest = 30

    private val owner: String by lazy { intent.extras.getString(getString(R.string.intent_repo_list_owner)) }

    private val viewer: String by lazy { intent.extras.getString(getString(R.string.intent_repo_list_viewer)) }

    private val defaultOrder = RepositoryOrder.builder()
            .direction(OrderDirection.DESC)
            .field(RepositoryOrderField.CREATED_AT)
            .build()

    private var order: RepositoryOrder = defaultOrder
        set(value) {
            field = value
            resetData()
        }

    private var cursor: String? = null
    private var hasNextPage = true

    private val data: MutableList<RepoItemQuery> = mutableListOf()
    private lateinit var adapter: RepoListAdapter
    private lateinit var listener: EndlessRecyclerViewScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)

        adapter = RepoListAdapter(data, viewer)

        val linearLayoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        repo_list.layoutManager = linearLayoutManager
        repo_list.adapter = adapter
        listener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                if (!hasNextPage) return
                loadMoreData()
            }
        }
        repo_list.addOnScrollListener(listener)
        // Data is loaded automatically since one of the options of the spinner will be auto-selected
        // loadMoreData()

        val arrayAdapter = ArrayAdapter(
                this,
                R.layout.simple_text_spinner_item,
                RepositoryOrderFieldDescriptiveWrapper.values()
        )
        repo_list_sorting_spinner.adapter = arrayAdapter
        repo_list_sorting_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                order = defaultOrder
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val wrapper = parent?.getItemAtPosition(position) as RepositoryOrderFieldDescriptiveWrapper
                order = RepositoryOrder.builder().direction(order.direction())
                        .field(wrapper.value).build()
            }
        }

        repo_list_sorting_switch.text = OrderDirectionDescriptiveWrapper.DESC.description
        repo_list_sorting_switch.setOnCheckedChangeListener { _, isChecked ->
            val direction = if (isChecked) {
                OrderDirectionDescriptiveWrapper.ASC
            } else {
                OrderDirectionDescriptiveWrapper.DESC
            }
            order = RepositoryOrder.builder().direction(direction.value)
                    .field(order.field()).build()
            repo_list_sorting_switch.text = direction.description
        }
    }

    suspend private fun fetchData(): Result<ReposQuery.Data> {
        val query = ReposQuery.builder()
                .first(maxItemPerRequest)
                .login(owner)
                .after(cursor)
                .order(order)
                .build()
        val deferred = async(CommonPool) { API.apolloClient.query(query).awaitResult() }
        return deferred.await()
    }

    private fun resetData() {
        cursor = null
        hasNextPage = true

        data.clear()
        adapter.notifyDataSetChanged()
        listener.resetState()
        loadMoreData()
    }

    private fun loadMoreData() {
        async(UI) {
            val result = fetchData()
            when (result) {
                is Result.Ok -> {
                    Log.d("Network", "Data Retrieved")
                    val repos = result.data.user()!!.repositories()
                    val pageInfoQuery = repos.pageInfo().fragments().pageInfoQuery()
                    hasNextPage = pageInfoQuery.hasNextPage()
                    cursor = pageInfoQuery.endCursor()
                    repos.nodes()?.let {
                        val start = data.size
                        data.addAll(it.map { it.fragments().repoItemQuery() })
                        adapter.notifyItemRangeInserted(start, it.size)
                    }
                }
                is Result.Error -> {
                    toast(result.errorMessage)
                }
            }
        }
    }

//    override fun onStop() {
//        async (CommonPool) {
//            val inst = User().queryFirst { it.equalTo("login", owner) }!!
//            for (repo in data) {
//                repo.updateRealm()
//                val repoInst = RealmRepo().queryFirst { it.equalTo("fullName", repo.fragments().repoItemQuery().nameWithOwner()) }!!
//                inst.repos.add(repoInst)
//            }
//        }
//        super.onStop()
//    }
}
