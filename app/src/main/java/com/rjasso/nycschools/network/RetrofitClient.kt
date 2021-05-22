package com.rjasso.nycschools.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    const val BASE_URL = "https://data.cityofnewyork.us/resource/"
    val instance by lazy {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        retrofit.create(API::class.java)
    }
}