package com.rjasso.nycschools

import android.util.Log
import androidx.lifecycle.ViewModel

class NYCSchoolsViewModel(val NYCSchoolsRepo: NYCSchoolsRepository) : ViewModel() {
    val TAG = NYCSchoolsViewModel::class.java.name

    fun getData() {
        Log.d(TAG,"getData()")
        NYCSchoolsRepo.getDataFromAPI()
    }
}