package com.example.aplikasigithubuser.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.aplikasigithubuser.database.UserEntity
import com.example.aplikasigithubuser.database.UserFavoriteDao
import com.example.aplikasigithubuser.database.UserFavoriteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UserFavoriteRepository(application: Application) {
    private val userFavoriteDao: UserFavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = UserFavoriteRoomDatabase.getDatabase(application)
        userFavoriteDao = db.userFavoriteDao()
    }

    fun getAllUsers(): LiveData<List<UserEntity>> = userFavoriteDao.getAllUsers()
    fun getUserById(id:Int) : LiveData<UserEntity?> = userFavoriteDao.getUserById(id)
    fun insert(userEntity: UserEntity) {
        executorService.execute { userFavoriteDao.insert(userEntity) }
    }
    fun delete(userEntity: UserEntity) {
        executorService.execute { userFavoriteDao.delete(userEntity) }
    }
    fun update(userEntity: UserEntity) {
        executorService.execute { userFavoriteDao.update(userEntity) }
    }
}