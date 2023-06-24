package com.example.suitmedia_test.di

import com.example.suitmedia_test.data.DataRepository
import com.example.suitmedia_test.network.ApiConfig

object Injection {
    fun provideRepository(): DataRepository {
        val apiService = ApiConfig.getApiService()
        return DataRepository(apiService)
    }
}