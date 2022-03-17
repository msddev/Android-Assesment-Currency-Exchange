package com.mkdev.remote.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RequestInterceptor : Interceptor {

    private val accessKey = "2712709b1b585dd153dbe1d747d88dc0"

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