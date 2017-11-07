package com.example.charlie.githubbrowser.ui

import UserQuery
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.charlie.githubbrowser.R
import com.example.charlie.githubbrowser.api.API
import com.example.charlie.githubbrowser.api.API.initApp
import com.example.charlie.githubbrowser.api.apollo.Result
import com.example.charlie.githubbrowser.api.apollo.awaitResult
import com.example.charlie.githubbrowser.models.realm.updateRealm
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.*
import java.net.URL
import java.security.SecureRandom

class MainActivity : Activity() {

    companion object {

        fun goToPage(activity: Activity, login: String) {
            activity.startActivity(activity.intentFor<MainActivity>(
                    activity.getString(R.string.intent_key_main_activity_redirect) to login
            ).clearTop())
        }
    }

    private lateinit var viewer: String

    private lateinit var adapter: ProfileListAdapter

    private lateinit var profileData: MutableMap<String, String?>

    private fun updateProfileData(map: MutableMap<String, String?>,
                                  name: String? = null,
                                  login: String? = null,
                                  biography: String? = null,
                                  email: String? = null,
                                  following: Int? = 0,
                                  follower: Int? = 0,
                                  repos: Int? = 0) {
        map[getString(R.string.profile_list_title_name)] = name
        map[getString(R.string.profile_list_title_login)] = login
        map[getString(R.string.profile_list_title_bio)] = biography
        map[getString(R.string.profile_list_title_email)] = email
        map[getString(R.string.profile_list_title_following)] = following.toString()
        map[getString(R.string.profile_list_title_follower)] = follower.toString()
        map[getString(R.string.profile_list_title_repos)] = repos.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        oAuth()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO: application level initialization, move when the launcher activity is changed
        initApp()

        // initialize data
        profileData = mutableMapOf<String, String?>().apply {
            updateProfileData(this)
        }

        adapter = ProfileListAdapter(applicationContext, profileData)

        profile_list.adapter = adapter
        profile_list.setOnItemClickListener { _, _, position, _ ->
            val owner = profileData[getString(R.string.profile_list_title_login)]
            when (adapter.order[position]) {
                getString(R.string.profile_list_title_repos) -> {
                    startActivity(intentFor<RepoListActivity>(
                            getString(R.string.intent_repo_list_owner) to owner,
                            getString(R.string.intent_repo_list_viewer) to viewer
                    ))
                }
                getString(R.string.profile_list_title_follower) -> {
                    startActivity(intentFor<FollowerListActivity>(
                            getString(R.string.intent_follower_list_owner) to owner,
                                    getString(R.string.intent_follower_list_viewer) to viewer
                    ))
                }
                getString(R.string.profile_list_title_following) -> {
                    startActivity(intentFor<FollowingListActivity>(
                            getString(R.string.intent_following_list_owner) to owner,
                            getString(R.string.intent_following_list_viewer) to viewer
                    ))
                }
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun oAuth() {
        val random = SecureRandom()
        val state = String(ByteArray(64 * 4).also(random::nextBytes))
        val url = Uri.parse("https://github.com/login/oauth/authorize").buildUpon()
                .appendQueryParameter("client_id", API.clientID)
                .appendQueryParameter("scope", "repo user admin:org")
                .appendQueryParameter("state", state)
                .build()

        val wv = fun (d: Dialog) = WebView(this).apply {
            settings.javaScriptEnabled = true
            loadUrl(url.toString())
            isFocusable = true
            isFocusableInTouchMode = true
            setOnTouchListener { v, event ->
                val action = event.action
                if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_UP) {
                    if (!v.hasFocus())
                    {
                        v.requestFocus()
                    }
                }
                false
            }

                webViewClient = object : WebViewClient() {

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)

                    if (url!!.startsWith(API.callbackURL)) {
                        val uri = Uri.parse(url)
                        val params = uri.queryParameterNames
                        if ("code" !in params || "state" !in params) {
                            toast("Authentication Failure, Retry Later")
                            Thread.sleep(1000)
                            System.exit(1)
                        }
                        val code = uri.getQueryParameter("code")
                        val receivedState = uri.getQueryParameter("state")
                        if (receivedState != state) {
                            toast("Possible Security Risk, Abort!")
                            Thread.sleep(1000)
                            System.exit(1)
                        }
                        async(UI) {
                            val response = async(CommonPool) { API.oAuth.getToken(code, state).execute() }.await()
                            if (!response.isSuccessful) {
                                toast("Authentication Failure, Retry Later")
                                Thread.sleep(1000)
                                System.exit(1)
                            }
                            API.initAPI(response.body()!!)
                            val result = async(CommonPool) {
                                API.apolloClient.query(ViewerQuery.builder().build()).awaitResult()
                            }.await()
                            if (result is Result.Ok) {
                                viewer = result.data.viewer().login()
                                redirectTo(viewer)
                            } else throw IllegalStateException()
                            d.dismiss()
                            this@MainActivity.title = "GithubBrowser"
                        }
                    }
                }
            }
        }
        AlertDialog.Builder(this).create().apply {
            title = "Authorize Github"
            setView(wv(this))
            setCancelable(false)
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        }.show()
    }

    class ProfileListAdapter(
            private val context: Context,
            private val profileData: MutableMap<String, String?>
    ) : BaseAdapter() {

        val order = listOf(
                context.getString(R.string.profile_list_title_name),
                context.getString(R.string.profile_list_title_login),
                context.getString(R.string.profile_list_title_bio),
                context.getString(R.string.profile_list_title_email),
                context.getString(R.string.profile_list_title_following),
                context.getString(R.string.profile_list_title_follower),
                context.getString(R.string.profile_list_title_repos)
        )

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = convertView ?: context.layoutInflater.inflate(R.layout.profile_list_item,
                    parent,
                    false)
            val title = view.findViewById<TextView>(R.id.item_title)
            val value = view.findViewById<TextView>(R.id.item_value)
            title.text = order[position]
            value.text = profileData[order[position]]
            return view
        }

        override fun getItem(position: Int): Any {
            val title = order[position]
            return title to profileData[title]
        }

        override fun getItemId(position: Int): Long {
            return getItem(position).hashCode().toLong()
        }

        override fun getCount(): Int {
            return order.size
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_option_menu, menu)
        return true
    }

    private fun handleIntent(intent: Intent?) {
        intent?.apply {
            val query = intent.getStringExtra(getString(R.string.intent_key_main_activity_redirect))
            fetchUserData(query ?: viewer)
        }
    }

    private fun fetchUserData(username: String) {
        val query = UserQuery.builder().login(username).build()
        async(UI) {
            val deferred = async(CommonPool) { API.apolloClient.query(query).awaitResult() }
            val result = deferred.await()
            when (result) {
                is Result.Ok -> {
                    val user = result.data.user()!!
                    // update database
                    async(CommonPool) {
                        user.updateRealm()
                    }
                    // update profile
                    updateProfileData(
                            profileData,
                            user.name(),
                            user.login(),
                            user.bio(),
                            user.email(),
                            user.following().totalCount(),
                            user.followers().totalCount(),
                            user.repositories().totalCount()
                    )
                    adapter.notifyDataSetChanged()
                    updateAvatar(user.avatarUrl())
                }
                is Result.Error -> {
                    toast(result.errorMessage)
                }
            }
        }
    }

    private suspend fun updateAvatar(url: URL) {
        val deferred = async(CommonPool) { BitmapFactory.decodeStream(url.openStream()) }
        profile_image.imageBitmap = deferred.await()
    }

    override fun onNewIntent(intent: Intent?) {
        this.intent = intent
        handleIntent(intent)
    }

    override fun onSearchRequested(): Boolean {
        startSearch(null, false, Bundle().apply {
            putString(getString(R.string.intent_repo_list_viewer), viewer)
        }, false)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.apply {
            when (itemId) {
                R.id.action_search -> onSearchRequested()
                R.id.action_home -> redirectTo(viewer)
                R.id.action_notfication -> startActivity(intentFor<NotificationListActivity>())
            }
        }
        return true
    }

    private fun redirectTo(login: String) {
        fetchUserData(login)
    }
}
