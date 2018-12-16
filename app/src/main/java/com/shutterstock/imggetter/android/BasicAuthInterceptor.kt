package com.shutterstock.imggetter.android

import okhttp3.Interceptor
import okhttp3.Response


class BasicAuthInterceptor(
    private val authToken: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain
            .request()
            .newBuilder()
            .addHeader("Authorization", authToken)
            .build()
        return chain.proceed(request)
    }
}