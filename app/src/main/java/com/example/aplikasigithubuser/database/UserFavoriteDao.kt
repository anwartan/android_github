package com.example.aplikasigithubuser.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserFavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(userEntity: UserEntity)
    @Update
    fun update(userEntity: UserEntity)
    @Delete
    fun delete(userEntity: UserEntity)
    @Query("SELECT * from UserEntity WHERE id = :id")
    fun getUserById(id:Int): LiveData<UserEntity?>

    @Query("SELECT * from UserEntity ORDER BY id ASC")
    fun getAllUsers(): LiveData<List<UserEntity>>
}