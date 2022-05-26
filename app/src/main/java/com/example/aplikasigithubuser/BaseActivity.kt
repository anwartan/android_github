package com.example.aplikasigithubuser

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

open class BaseActivity: AppCompatActivity()  {
    fun getDataStore(): DataStore<Preferences> {
        return dataStore
    }
}