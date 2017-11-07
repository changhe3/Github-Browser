package com.example.charlie.githubbrowser.models.realm

import com.vicpin.krealmextensions.query
import com.vicpin.krealmextensions.queryFirst
import com.vicpin.krealmextensions.save
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User(
        @PrimaryKey open var login: String = "",
        open var name: String = "",
        open var bio: String = "",
        open var email: String = "",
        open var avatarUrl: String = "",
        open var following: RealmList<User> = RealmList(),
        open var follower: RealmList<User> = RealmList(),
        open var repos: RealmList<Repository> = RealmList()
): RealmObject()

fun UserQuery.User.updateRealm() {
    val query = User().queryFirst { it.equalTo("login", login()) } ?: User(login = login())
    query.name = name().orEmpty()
    query.bio = bio().orEmpty()
    query.email = email()
    query.avatarUrl = avatarUrl().toExternalForm()
    query.save()
}