package com.mkdev.remote.api

import com.mkdev.remote.models.RateResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FourSquareService {

    @GET("v1/latest")
    suspend fun getNearVenues(
        @Query("format") format: Int = 1
    ): RateResponse
}
