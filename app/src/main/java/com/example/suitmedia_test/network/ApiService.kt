package com.example.suitmedia_test.network

import com.example.suitmedia_test.response.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getData(
        @Query("page") page: Int,
        @Query("per_page") size: Int
    ): Response
}