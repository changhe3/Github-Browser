package com.example.charlie.githubbrowser.ui

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.charlie.githubbrowser.R
import com.example.charlie.githubbrowser.api.notifications.models.Notification
import kotlinx.android.synthetic.main.notification_list_item.view.*
import org.jetbrains.anko.browse
import util.android.inflate
import java.time.LocalDateTime


class NotificationListAdapter(private val data: List<Notification>) : RecyclerView.Adapter<NotificationListAdapter.ViewHolder>() {

    companion object {
        private val reasons = mapOf<String, String>(
                "assign" to "You were assigned to the Issue.",
                "author" to "You created the thread.",
                "comment" to "You commented on the thread.",
                "invitation" to "You accepted an invitation to contribute to the repository.",
                "manual" to "You subscribed to the thread (via an Issue or Pull Request).",
                "mention" to "You were specifically @mentioned in the content.",
                "state_change" to "You changed the thread state (for example, closing an Issue or merging a Pull Request).",
                "subscribed" to "You're watching the repository.",
                "team_mention" to "You were on a team that was mentioned."
        )
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Notification) = with(itemView) {
            repo_label.text = item.repository.fullName
            repo_label.setOnClickListener { view ->
                view.context.browse(item.repository.url)
            }
            subject_label.text = "Title: ${item.subject.title}  Type: ${item.subject.type}"
            reason_label.text = "Reason: ${reasons[item.reason]}"
            time_label.text = item.updatedAt.toGMTString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder = ViewHolder(parent!!.inflate(R.layout.notification_list_item))

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}