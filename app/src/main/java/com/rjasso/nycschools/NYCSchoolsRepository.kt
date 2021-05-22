package com.rjasso.nycschools

import android.util.Log
import com.rjasso.nycschools.model.SchoolList
import com.rjasso.nycschools.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class NYCSchoolsRepository {
    val TAG = NYCSchoolsRepository::class.java.name
    fun getDataFromDB() {
        Log.d(TAG,"getDataFromDB()")
    }
    fun getDataFromAPI() {
        Log.d(TAG,"getDataFromAPI()")
        RetrofitClient.instance.getSchools().enqueue(object: Callback<SchoolList>{
            override fun onResponse(call: Call<SchoolList>, response: Response<SchoolList>) {
                Log.d(TAG,"onResponse(): " + response.body()?.size)
            }

            override fun onFailure(call: Call<SchoolList>, t: Throwable) {
                Log.d(TAG,"onFailure(): " + t.message)
            }
        })
    }
}
