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
        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .header("apikey", apiKey)
            .build()
        chain.proceed(modifiedRequest)
    }).build()
}