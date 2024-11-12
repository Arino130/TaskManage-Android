package com.ctp.zentasks.domain.usecase

import com.ctp.zentasks.domain.repository.ISharedRepository
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