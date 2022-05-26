package com.example.aplikasigithubuser.model

import android.os.Parcelable
import com.example.aplikasigithubuser.database.UserEntity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("html_url")
    val htmlUrl: String,

    @field:SerializedName("following_url")
    val followingUrl: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("followers_url")
    val followersUrl: String,

    @field:SerializedName("gravatar_id")
    val gravatarId: String,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("node_id")
    val nodeId: String,

    @field:SerializedName("repos_url")
    val reposUrl: String,

    @field:SerializedName("following")
    val following: Int? = null,

    @field:SerializedName("followers")
    val followers: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("location")
    val location: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("company")
    val company: String? = null,

    ) : Parcelable

fun User.toUserEntity() = UserEntity(
    id,
    login,
    avatarUrl,
    htmlUrl,
    followingUrl,
    followersUrl,
    gravatarId,
    url,
    nodeId,
    reposUrl,
    following,
    followers,
    name,
    location,
    email,
    company
)
