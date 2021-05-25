package com.rjasso.nycschools

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rjasso.nycschools.model.SchoolList
import com.rjasso.nycschools.model.SchoolListItem
import com.rjasso.nycschools.database.NYCSchoolDB
import com.rjasso.nycschools.model.SchoolSAT
import com.rjasso.nycschools.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NYCSchoolsRepository(val schoolsDB: NYCSchoolDB) {
    val TAG = NYCSchoolsRepository::class.java.name

    private var schools: MutableLiveData<List<SchoolListItem>> = MutableLiveData()

    fun getDataFromDB() {
        Log.d(TAG,"getDataFromDB()")
        schools.postValue(schoolsDB.schoolsDAO().getAll())
    }
    fun getDataFromAPI() {
        Log.d(TAG,"getDataFromAPI()")
        RetrofitClient.instance.getSchools().enqueue(object: Callback<SchoolList>{
            override fun onResponse(call: Call<SchoolList>, response: Response<SchoolList>) {
                Log.d(TAG,"onResponse(): " + response.body())
                schools.postValue(response.body())
                schoolsDB.schoolsDAO().deleteAll()
                schoolsDB.schoolsDAO().insertAll(response.body() as List<SchoolListItem>)
            }

            override fun onFailure(call: Call<SchoolList>, t: Throwable) {
                Log.d(TAG,"onFailure(): " + t.message)
            }
        })
    }

    fun getSchools(): LiveData<List<SchoolListItem>> {
        return schools
    }

    fun getSATData(dbn: String) {
        Log.d(TAG,"getSATData()")
        RetrofitClient.instance.getSAT(dbn).enqueue(object: Callback<SchoolSAT>{
            override fun onResponse(call: Call<SchoolSAT>, response: Response<SchoolSAT>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<SchoolSAT>, t: Throwable) {
                Log.d(TAG,"onFailure(): " + t.message)
            }

        })
    }

}
