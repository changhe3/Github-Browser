package com.example.charlie.githubbrowser.ui

import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.charlie.githubbrowser.R
import fragment.UserListItemQuery
import kotlinx.android.synthetic.main.user_list_item.view.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.imageBitmap
import util.android.inflate

class UserListAdapter(private val data: List<UserListItemQuery>, private val redirectTo: (String) -> Unit) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View, private val redirectTo: (String) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: UserListItemQuery) = with(itemView) {
            user_list_item.setOnClickListener {
                redirectTo(item.login())
            }
            user_login.text = item.login()
            async(UI) {
                val deferred = async(CommonPool) { BitmapFactory.decodeStream(item.avatarUrl().openStream()) }
                user_avatar.imageBitmap = deferred.await()
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder
            = ViewHolder(parent!!.inflate(R.layout.user_list_item), redirectTo)
}