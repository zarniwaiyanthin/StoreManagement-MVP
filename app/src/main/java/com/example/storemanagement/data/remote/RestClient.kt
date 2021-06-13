package com.example.storemanagement.data.remote

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {

    fun getApiService(): ApiService {
        return getRetrofit()
            .create(ApiService::class.java)
    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://128.199.96.212/product-api")
            .client(getClient())
            .addConverterFactory(getConverter())
            .build()
    }

    private fun getClient(): OkHttpClient {
        val loggingInterceptor=HttpLoggingInterceptor()
        loggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    }

    private fun getConverter(): Converter.Factory {
        val gson=GsonBuilder()
            .create()
        return GsonConverterFactory.create(gson)
    }
}