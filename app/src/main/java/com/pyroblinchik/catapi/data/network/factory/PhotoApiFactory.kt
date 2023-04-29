package com.pyroblinchik.catapi.data.network.factory

import android.util.Log
import com.google.gson.GsonBuilder
import com.pyroblinchik.catapi.data.Constants.BASE_URL
import com.pyroblinchik.catapi.data.network.services.BreedService
import com.pyroblinchik.catapi.data.network.services.PhotoService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PhotoApiFactory {
    var gson = GsonBuilder()
        .setLenient()
        .create()
    private val httpClient =
        OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
            val original = chain.request()
            val request =
                original.newBuilder()
                    .method(original.method, original.body)
                    .build()
            chain.proceed(request)
        })
    private val client = httpClient.build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()


    val apiService = retrofit.create(PhotoService::class.java)
}
