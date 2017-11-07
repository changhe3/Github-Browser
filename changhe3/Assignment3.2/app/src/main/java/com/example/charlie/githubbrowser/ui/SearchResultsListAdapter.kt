package com.example.charlie.githubbrowser.ui

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.charlie.githubbrowser.R
import com.example.charlie.githubbrowser.models.apollo.Repository
import fragment.UserListItemQuery
import type.SearchType
import util.android.inflate

class SearchResultsListAdapter(private val data: List<SearchQuery.Node>,
                               private val viewer: String,
                               private val redirectTo: (String) -> Unit,
                               private val searchType: SearchType) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder?.let {
            when (searchType) {
                SearchType.USER -> {
                    val vh = it as UserListAdapter.ViewHolder
                    vh.bind(data[position].asUser()!!.fragments().userListItemQuery()!!)
                }
                SearchType.REPOSITORY -> {
                    val vh = it as RepoListAdapter.ViewHolder
                    vh.bind(data[position].asRepository()!!.fragments().repoItemQuery()!!)
                }
                else -> throw IllegalStateException()
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
            when(searchType) {
                SearchType.USER -> UserListAdapter.ViewHolder(parent!!.inflate(R.layout.user_list_item), redirectTo)
                SearchType.REPOSITORY -> RepoListAdapter.ViewHolder(parent!!.inflate(R.layout.repo_list_item), viewer)
                else -> throw IllegalStateException()
            }
}