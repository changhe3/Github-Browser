package com.example.charlie.githubbrowser.models.realm

import com.example.charlie.githubbrowser.models.apollo.Repository as ApolloRepo
import com.vicpin.krealmextensions.queryFirst
import com.vicpin.krealmextensions.save
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Repository(
        @PrimaryKey open var fullName: String = "",
        open var url: String = "",
        open var starred: Boolean = false
): RealmObject()

fun ApolloRepo.updateRealm(): Unit {
    val repoItemQuery = fragments().repoItemQuery()
    val query = Repository().queryFirst {
        it.equalTo("fullName", repoItemQuery.nameWithOwner())
    } ?: Repository(fullName = repoItemQuery.nameWithOwner())
    query.url = repoItemQuery.url().toExternalForm()
    query.starred = repoItemQuery.viewerHasStarred()
    query.save()
}