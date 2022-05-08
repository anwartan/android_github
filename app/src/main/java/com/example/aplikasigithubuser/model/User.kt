package com.example.aplikasigithubuser.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(
    var avatar: Int,
    var username: String,
    var name: String,
    var follower:Int,
    var following:Int,
    var location:String,
    var repository:String,
    var company:String
):Parcelable