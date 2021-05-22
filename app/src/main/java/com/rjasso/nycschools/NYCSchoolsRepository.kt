package com.rjasso.nycschools

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rjasso.nycschools.model.SchoolList
import com.rjasso.nycschools.model.SchoolListItem
import com.rjasso.nycschools.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NYCSchoolsRepository {
    val TAG = NYCSchoolsRepository::class.java.name

    private val schools: MutableLiveData<List<SchoolListItem>> by lazy {
        MutableLiveData<List<SchoolListItem>>().also {
            getDataFromDB()
            getDataFromAPI()
        }
    }

    fun getDataFromDB() {
        Log.d(TAG,"getDataFromDB()")
    }
    fun getDataFromAPI() {
        Log.d(TAG,"getDataFromAPI()")
        RetrofitClient.instance.getSchools().enqueue(object: Callback<SchoolList>{
            override fun onResponse(call: Call<SchoolList>, response: Response<SchoolList>) {
                Log.d(TAG,"onResponse(): " + response.body())
                schools.value = response.body()
            }

            override fun onFailure(call: Call<SchoolList>, t: Throwable) {
                Log.d(TAG,"onFailure(): " + t.message)
            }
        })
    }

    fun getSchools(): LiveData<List<SchoolListItem>> {
        return schools
    }

}
