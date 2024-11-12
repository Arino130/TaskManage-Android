package com.ctp.zentasks.domain.repository

interface ISharedRepository {
    fun saveBoolean(key: String, value: Boolean)
    fun getBoolean(key: String, defaultValue: Boolean): Boolean
}