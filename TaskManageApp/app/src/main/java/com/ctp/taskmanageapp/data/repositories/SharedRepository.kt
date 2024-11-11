package com.ctp.taskmanageapp.data.repositories

import android.content.SharedPreferences
import com.ctp.taskmanageapp.domain.repository.ISharedRepository
import javax.inject.Inject

class SharedRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ISharedRepository {
    override fun saveBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }
}