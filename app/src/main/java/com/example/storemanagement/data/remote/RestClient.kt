package com.example.storemanagement.data.remote

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RestClient {

    fun getApiService(): ApiService {
        return getRetrofit()
            .create(ApiService::class.java)
    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://talentnest.com.mm/product-api/")
            .client(getClient())
            .addConverterFactory(getConverter())
            .build()
    }

    private fun getClient(): OkHttpClient {
        val loggingInterceptor=HttpLoggingInterceptor()
        loggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
                .callTimeout(60,TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .connectTimeout(60,TimeUnit.SECONDS)
            .build()

    }

    private fun getConverter(): Converter.Factory {
        val gson=GsonBuilder()
            .create()
        return GsonConverterFactory.create(gson)
    }
}