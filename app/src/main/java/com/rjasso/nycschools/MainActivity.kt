package com.rjasso.nycschools

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rjasso.nycschools.database.NYCSchoolDB
import com.rjasso.nycschools.model.SchoolListItem

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.name

    private lateinit var viewModel: NYCSchoolsViewModel
    private lateinit var NYCSchoolRepo: NYCSchoolsRepository
    private lateinit var schoolRecyclerView: RecyclerView
    private val schoolList = mutableListOf<SchoolListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")
        setContentView(R.layout.activity_main)
        schoolRecyclerView = findViewById(R.id.schoolRecyclerView)
        schoolRecyclerView.adapter = SchoolAdapter(schoolList)
        schoolRecyclerView.layoutManager = LinearLayoutManager(this)
        NYCSchoolRepo = NYCSchoolsRepository(NYCSchoolDB.getInstance(this))
        viewModel = ViewModelProvider(this, NYCSchoolsViewModelFactory(NYCSchoolRepo)).get(NYCSchoolsViewModel::class.java)
        viewModel.getSchools().observe(this, object: Observer<List<SchoolListItem>> {
            override fun onChanged(schools: List<SchoolListItem>?) {
                schoolList.clear()
                schools.let {
                    if (it != null) {
                        schoolList.addAll(it)
                    }
                }
            }
        })
        schoolList


        viewModel.getData()
//        viewModel.getSATData("21K728")
    }
}