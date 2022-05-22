package com.example.aplikasigithubuser.data.datasource

import com.example.aplikasigithubuser.model.SearchModelResponse
import com.example.aplikasigithubuser.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserDataSource {
    @GET("search/users")
    fun getSearchUser(
        @Query("q") username: String
    ): Call<SearchModelResponse>

    @GET("users/{username}")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<User>

    @GET("users/{username}/followers")
    fun getListFollower(
        @Path("username") username: String
    ): Call<List<User>>

    @GET("users/{username}/following")
    fun getListFollowing(
        @Path("username") username: String
    ): Call<List<User>>
}