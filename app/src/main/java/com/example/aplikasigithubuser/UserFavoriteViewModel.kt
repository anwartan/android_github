package com.example.aplikasigithubuser

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.aplikasigithubuser.database.UserEntity
import com.example.aplikasigithubuser.repository.UserFavoriteRepository

class UserFavoriteViewModel(application: Application):ViewModel() {
    private val userFavoriteRepository: UserFavoriteRepository = UserFavoriteRepository(application)

    fun insert(userEntity: UserEntity) {
        userFavoriteRepository.insert(userEntity)
    }
    fun update(userEntity: UserEntity) {
        userFavoriteRepository.update(userEntity)
    }
    fun delete(userEntity: UserEntity) {
        userFavoriteRepository.delete(userEntity)
    }

    fun getAllFavoriteUsers(): LiveData<List<UserEntity>> = userFavoriteRepository.getAllUsers()

    fun isAddedFavoriteUser(id:Int): LiveData<UserEntity?> {
        return userFavoriteRepository.getUserById(id)
    }
}