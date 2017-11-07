package com.example.charlie.githubbrowser.ui

import FollowersQuery
import android.util.Log
import com.example.charlie.githubbrowser.R
import com.example.charlie.githubbrowser.api.API
import com.example.charlie.githubbrowser.api.apollo.Result
import com.example.charlie.githubbrowser.api.apollo.awaitResult
import com.example.charlie.githubbrowser.models.realm.User
import com.vicpin.krealmextensions.query
import com.vicpin.krealmextensions.queryFirst
import com.vicpin.krealmextensions.save
import fragment.PageInfoQuery
import fragment.UserListItemQuery
import io.realm.Realm
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class FollowerListActivity : UserListActivity() {

    private val owner by lazy { intent.extras.getString(getString(R.string.intent_follower_list_owner)) }

    private val viewer by lazy { intent.extras.getString(getString(R.string.intent_follower_list_viewer)) }

    suspend override fun fetchData(): Result<Pair<List<UserListItemQuery>, PageInfoQuery>> {
        val query = FollowersQuery.builder()
                .login(owner)
                .first(maxItemPerRequest)
                .after(cursor)
                .build()
        val result = async(CommonPool) { API.apolloClient.query(query).awaitResult() }.await()
        return result.map {
            val followers = it.user()!!.followers()
            Pair(followers.nodes().orEmpty().map { it.fragments().userListItemQuery() },
                    followers.pageInfo().fragments().pageInfoQuery())
        }
    }

    override fun onStop() {
        async (CommonPool) {
            val inst = User().queryFirst { it.equalTo("login", owner) }!!
            for (follower in data) {
                val followerInst = User().queryFirst { it.equalTo("login", follower.login()) } ?:
                        User(login = follower.login(), avatarUrl = follower.avatarUrl().toExternalForm())
                followerInst.avatarUrl = follower.avatarUrl().toExternalForm()
                followerInst.save()
                inst.follower.add(followerInst)
            }
        }
        super.onStop()
    }
}
