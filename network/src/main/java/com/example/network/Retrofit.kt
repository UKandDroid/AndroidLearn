package com.example.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://app.ticketmaster.com"

class Retrofit  {

    fun provideApi(apiKey: String):RemoteApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient(apiKey))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RemoteApi::class.java)


    private fun httpClient(apiKey: String) = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
        val request = chain.request()
        val urlWithApi = request.url.newBuilder().apply {
                addQueryParameter("apikey", apiKey)
        }.build()

        val newRequest = request.newBuilder()
            .url(urlWithApi)
            .build()
        chain.proceed(newRequest)
    }).build()
}