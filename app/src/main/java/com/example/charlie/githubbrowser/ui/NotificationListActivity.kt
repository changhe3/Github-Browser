package com.example.charlie.githubbrowser.ui

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.example.charlie.githubbrowser.R
import com.example.charlie.githubbrowser.api.API
import com.example.charlie.githubbrowser.api.notifications.NotificationServices
import com.example.charlie.githubbrowser.api.notifications.models.Notification
import io.realm.internal.android.ISO8601Utils
import kotlinx.android.synthetic.main.activity_notification_list.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.toast
import ru.gildor.coroutines.retrofit.Result
import ru.gildor.coroutines.retrofit.awaitResult
import java.text.SimpleDateFormat
import java.util.*

class NotificationListActivity : Activity() {

    private val data = mutableListOf<Notification>()
    private lateinit var adapter: NotificationListAdapter
    private var lastChecked: Date = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_list)
        adapter = NotificationListAdapter(data)
        notification_list.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        notification_list.adapter = adapter
        loadData()
    }

    private fun loadData() {
        async(UI) {
            val result = async(CommonPool) { API.notificationServices.getNotifications().awaitResult() }.await()
            when (result) {
                is Result.Ok -> {
                    lastChecked = Date()
                    val start = data.size
                    data.addAll(result.value)
                    adapter.notifyItemRangeInserted(start, result.value.size)
                }
                is Result.Error -> {
                    toast(result.response.message())
                }
                is Result.Exception -> {
                    toast(result.exception.localizedMessage)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.notification_option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.apply {
            when (itemId) {
                R.id.notification_action_refresh -> {
                    reset()
                }
                R.id.notification_action_mark_as_read -> {
                    val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US) // Quoted "Z" to indicate UTC, no timezone offset
                    df.timeZone = TimeZone.getTimeZone("UTC")
                    async(UI) {
                        val result = async(CommonPool) {
                            API.notificationServices
                                    .markAsRead(NotificationServices.MarkAsReadBody(df.format(lastChecked)))
                                    .awaitResult()
                        }.await()
                        when (result) {
                            is Result.Error -> {
                                Log.e("notification", result.response.message())
                            }
                            is Result.Exception -> {
                                Log.e("notification", result.exception.localizedMessage)
                            }
                        }
                        reset()
                    }
                }
            }
        }
        return true
    }

    private fun reset() {
        val size = data.size
        data.clear()
        adapter.notifyItemRangeRemoved(0, size)
        loadData()
    }
}
