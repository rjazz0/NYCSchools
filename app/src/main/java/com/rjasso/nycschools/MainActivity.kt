package com.rjasso.nycschools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.name

    private lateinit var viewModel: NYCSchoolsViewModel
    private val NYCSchoolRepo = NYCSchoolsRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, NYCSchoolsViewModelFactory(NYCSchoolRepo)).get(NYCSchoolsViewModel::class.java)
        viewModel.getData()
    }
}