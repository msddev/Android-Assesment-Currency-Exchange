package com.mkdev.remote.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceFactory {

    private const val baseUrl = "http://api.exchangeratesapi.io"

    fun create(isDebug: Boolean): FourSquareService {
        val retrofit = createRetrofit(isDebug)
        return retrofit.create(FourSquareService::class.java)
    }

    private fun createRetrofit(
        isDebug: Boolean
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                createOkHttpClient(
                    createLoggingInterceptor(isDebug),
                    createFourSquareInterceptor()
                )
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        fourSquareInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(fourSquareInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    private fun createFourSquareInterceptor(): Interceptor = RequestInterceptor()

    private fun createLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (isDebug) {
                HttpLoggingInterceptor.Level.BASIC
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    private const val OK_HTTP_TIMEOUT = 30L
}
