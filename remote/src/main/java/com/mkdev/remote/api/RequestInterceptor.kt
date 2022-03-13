package com.mkdev.remote.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RequestInterceptor : Interceptor {

    private val accessKey = "4cca9a4e9d792dcfc8c665e9c87686ab"

    override fun intercept(chain: Interceptor.Chain): Response {

        val url = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("access_key", accessKey)
            .build()

        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }

}