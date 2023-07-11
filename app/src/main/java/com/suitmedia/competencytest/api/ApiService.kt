package com.suitmedia.competencytest.api

import com.suitmedia.competencytest.apiresponse.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsers(
        @Query("page")page: Int,
        @Query("per_page") per_page: Int
    ): Response<ApiResponse>
}