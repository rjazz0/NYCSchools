package com.rjasso.nycschools

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rjasso.nycschools.model.SchoolListItem
import com.rjasso.nycschools.model.SchoolSATItem

class NYCSchoolsViewModel(val repository: NYCSchoolsRepository) : ViewModel() {
    val TAG = NYCSchoolsViewModel::class.java.name

    fun getData() {
        repository.getDataFromDB()
        repository.getDataFromAPI()
    }

    fun getSchools(): LiveData<List<SchoolListItem>> {
        Log.d(TAG, "getSchools()")
        return repository.getSchools()
    }

    fun getSATData(): LiveData<SchoolSATItem>{
        Log.d(TAG, "getSATData()")
        return repository.getSchoolsSAT()
    }

    fun getSATDataObject(dbn: String) {
        repository.getSATData(dbn)
    }
}