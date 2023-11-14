package com.adorastudios.graphqlevaluationassignment.data.network

import com.adorastudios.graphqlevaluationassignment.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        requestBuilder.header("Authorization", BuildConfig.GRAPHQL_API_KEY)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
