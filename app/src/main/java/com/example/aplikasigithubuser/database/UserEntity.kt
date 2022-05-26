package com.example.aplikasigithubuser.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.aplikasigithubuser.model.User
import kotlinx.parcelize.Parcelize


@Entity
@Parcelize
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "login")
    var login: String? = null,

    @ColumnInfo(name = "avatarUrl")
    var avatarUrl: String? = null,

    @ColumnInfo(name = "htmlUrl")
    var htmlUrl: String? = null,

    @ColumnInfo(name = "followingUrl")
    var followingUrl: String? = null,

    @ColumnInfo(name = "followersUrl")
    var followersUrl: String? = null,

    @ColumnInfo(name = "gravatarId")
    var gravatarId: String? = null,

    @ColumnInfo(name = "url")
    var url: String? = null,

    @ColumnInfo(name = "nodeId")
    var nodeId: String? = null,

    @ColumnInfo(name = "reposUrl")
    var reposUrl: String? = null,

    @ColumnInfo(name = "following")
    var following: Int? = 0,

    @ColumnInfo(name = "followers")
    var followers: Int? = 0,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "location")
    var location: String? = null,

    @ColumnInfo(name = "email")
    var email: String? = null,

    @ColumnInfo(name = "company")
    var company: String? = null
) : Parcelable

fun UserEntity.toUser() = User(
    name = name,
    email = email,
    avatarUrl = avatarUrl ?: "",
    company = company,
    followers = followers,
    following = following,
    followersUrl = followersUrl ?: "",
    followingUrl = followingUrl ?: "",
    gravatarId = gravatarId ?: "",
    htmlUrl = htmlUrl ?: "",
    id = id,
    location = location ?: "",
    login = login ?: "",
    nodeId = nodeId ?: "",
    reposUrl = reposUrl ?: "",
    url = url ?: ""
)
