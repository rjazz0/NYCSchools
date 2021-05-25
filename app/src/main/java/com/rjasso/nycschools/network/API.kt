package com.rjasso.nycschools.network

import com.rjasso.nycschools.model.SchoolList
import com.rjasso.nycschools.model.SchoolSAT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("s3k6-pzi2.json")
    fun getSchools(): Call<SchoolList>

    @GET("f9bf-2cp4.json")
    fun getSAT(@Query("dbn") dbn: String): Call<SchoolSAT>
}