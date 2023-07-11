package com.suitmedia.competencytest.utils

import android.content.Context
import com.suitmedia.competencytest.api.ApiConfig
import com.suitmedia.competencytest.data.ApiRepository

object Injection {
    fun provideRepository(context: Context): ApiRepository {
        val apiService = ApiConfig().getApiService()
        return ApiRepository(apiService)
    }
}