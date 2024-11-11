package com.ctp.taskmanageapp.domain.usecase

import com.ctp.taskmanageapp.domain.repository.ISharedRepository
import javax.inject.Inject

class SharedUseCases @Inject constructor(
    private val sharedRepository: ISharedRepository
) {
    fun saveBoolean(key: String, value: Boolean) {
        sharedRepository.saveBoolean(key, value)
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedRepository.getBoolean(key, defaultValue)
    }
}