package com.example.charlie.githubbrowser.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.charlie.githubbrowser.R
import com.example.charlie.githubbrowser.api.API
import com.example.charlie.githubbrowser.api.apollo.awaitResult
import com.example.charlie.githubbrowser.models.apollo.Repository
import fragment.RepoItemQuery
import kotlinx.android.synthetic.main.repo_list_item.view.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.browse
import org.jetbrains.anko.intentFor
import util.android.inflate

class RepoListAdapter(private val dataList: List<RepoItemQuery>, private val viewer: String) : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {


    class ViewHolder(itemView: View, val viewer: String) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: RepoItemQuery) = with(itemView) {
            repo_name.text = item.nameWithOwner()
            repo_url.text = item.url().toExternalForm()
            repo_url.setOnClickListener { view ->
                view.context.browse(item.url().toExternalForm())
            }
            repo_lang_details.setOnClickListener { view ->
                view.context.apply {
                    startActivity(intentFor<LanguageActivity>(
                            getString(R.string.intent_language_activity_id) to item.id()
                    ))
                }
            }
            repo_list_star_status.setOnCheckedChangeListener(null)
            repo_list_star_status.isChecked = item.viewerHasStarred()
            repo_list_star_status.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    val mutation = AddStarMutation.builder()
                            .clientId(viewer)
                            .starrableId(item.id())
                            .build()
                    async(UI) {
                        val deferred = async(CommonPool) { API.apolloClient.mutate(mutation).awaitResult() }
                        deferred.await()
                    }
                } else {
                    val mutation = RemoveStarMutation.builder()
                            .clientId(viewer)
                            .starrableId(item.id())
                            .build()
                    async(UI) {
                        val deferred = async(CommonPool) { API.apolloClient.mutate(mutation).awaitResult() }
                        deferred.await()
                    }
                }
            }
        }
    }

    init {
        setHasStableIds(true)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder
            = ViewHolder(parent!!.inflate(R.layout.repo_list_item), viewer)
}