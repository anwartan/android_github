package com.example.aplikasigithubuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aplikasigithubuser.common.ApiConfig
import com.example.aplikasigithubuser.event.Event
import com.example.aplikasigithubuser.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    companion object{
        private const val TAG = "DetailViewModel"
    }
    private val _user = MutableLiveData<User>()
    val user : LiveData<User> = _user

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading:LiveData<Boolean> = _isLoading

    private val _errorText = MutableLiveData<Event<String>>()
    val errorText: LiveData<Event<String>> = _errorText

    private val _followers = MutableLiveData<List<User>>()
    val followers:LiveData<List<User>> = _followers

    private val _following = MutableLiveData<List<User>>()
    val followings:LiveData<List<User>> = _following

    fun fetchUserDetail(username:String){
        showLoading(true)
        val client = ApiConfig.getUserDataSource().getUserDetail(username)
        client.enqueue(object : Callback<User> {
            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                showLoading(false)
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    Log.e(TAG, "detail: $responseBody")
                    _user.value= responseBody
                } else {
                    _errorText.value = Event(response.message())
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                showLoading(false)
                _errorText.value = Event(t.message?:"Failure Get Data")
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun fetchListFollower(username:String){
        showLoading(true)
        val client = ApiConfig.getUserDataSource().getListFollower(username)
        client.enqueue(object : Callback<List<User>> {
            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>
            ) {
                showLoading(false)
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {

                    _followers.value= responseBody
                } else {
                    _errorText.value = Event(response.message())
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                showLoading(false)
                _errorText.value = Event(t.message?:"Failure Get Data")
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun fetchListFollowing(username:String){
        showLoading(true)
        val client = ApiConfig.getUserDataSource().getListFollowing(username)
        client.enqueue(object : Callback<List<User>> {
            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>
            ) {
                showLoading(false)
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {

                    _following.value= responseBody
                } else {
                    _errorText.value = Event(response.message())
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                showLoading(false)
                _errorText.value = Event(t.message?:"Failure Get Data")
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }


    private fun showLoading(b: Boolean) {
        _isLoading.value=b
    }
}