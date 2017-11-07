package com.example.charlie.githubbrowser.ui

import FollowingQuery
import com.example.charlie.githubbrowser.R
import com.example.charlie.githubbrowser.api.API
import com.example.charlie.githubbrowser.api.apollo.Result
import com.example.charlie.githubbrowser.api.apollo.awaitResult
import com.example.charlie.githubbrowser.models.realm.User
import com.vicpin.krealmextensions.queryFirst
import com.vicpin.krealmextensions.save
import fragment.PageInfoQuery
import fragment.UserListItemQuery
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class FollowingListActivity : UserListActivity() {

    private val owner by lazy { intent.extras.getString(getString(R.string.intent_following_list_owner)) }

    private val viewer by lazy { intent.extras.getString(getString(R.string.intent_following_list_viewer)) }

    suspend override fun fetchData(): Result<Pair<List<UserListItemQuery>, PageInfoQuery>> {
        val query = FollowingQuery.builder()
                .login(owner)
                .first(maxItemPerRequest)
                .after(cursor)
                .build()
        val result = async(UI) { API.apolloClient.query(query).awaitResult() }.await()
        return result.map {
            val followers = it.user()!!.following()
            Pair(followers.nodes().orEmpty().map { it.fragments().userListItemQuery() },
                    followers.pageInfo().fragments().pageInfoQuery())
        }
    }

    override fun onStop() {
        async (CommonPool) {
            val inst = User().queryFirst { it.equalTo("login", owner) }!!
            for (following in data) {
                val followingInst = User().queryFirst { it.equalTo("login", following.login()) } ?:
                        User(login = following.login(), avatarUrl = following.avatarUrl().toExternalForm())
                followingInst.avatarUrl = following.avatarUrl().toExternalForm()
                followingInst.save()
                inst.following.add(followingInst)
            }
        }
        super.onStop()
    }
}
