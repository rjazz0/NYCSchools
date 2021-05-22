package com.rjasso.nycschools.network

import com.rjasso.nycschools.model.SchoolList
import retrofit2.Call
import retrofit2.http.GET

interface API {
    @GET("s3k6-pzi2.json")
    fun getSchools(): Call<SchoolList>
}