package com.adorastudios.graphqlevaluationassignment.data.network

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient

class LeadsApi {

    fun getApolloClient(): ApolloClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .build()
        return ApolloClient.Builder()
            .serverUrl("http://54.246.238.84:3000/graphql")
            .okHttpClient(okHttpClient)
            .build()
    }
}
