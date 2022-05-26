package com.example.aplikasigithubuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aplikasigithubuser.common.ApiConfig
import com.example.aplikasigithubuser.event.Event
import com.example.aplikasigithubuser.model.SearchModelResponse
import com.example.aplikasigithubuser.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {


    private val _users = MutableLiveData<List<User>>(listOf())
    val users: LiveData<List<User>> = _users

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading:LiveData<Boolean> = _isLoading

    private val _errorText = MutableLiveData<Event<String>>()
    val errorText: LiveData<Event<String>> = _errorText


    fun fetchSearchUser(username:String){
        showLoading(true)
        val client = ApiConfig.getUserDataSource().getSearchUser(username)
        client.enqueue(object : Callback<SearchModelResponse> {
            override fun onResponse(
                call: Call<SearchModelResponse>,
                response: Response<SearchModelResponse>
            ) {
                showLoading(false)
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {

                    _users.value= responseBody.items
                } else {
                    _errorText.value = Event(response.message())
                }
            }
            override fun onFailure(call: Call<SearchModelResponse>, t: Throwable) {
                showLoading(false)
                _errorText.value = Event(t.message?:"Failure Get Data")
            }
        })
    }

    fun showLoading(b: Boolean) {
        _isLoading.value=b
    }
    companion object{
        private const val TAG = "MainViewModel"
    }

}