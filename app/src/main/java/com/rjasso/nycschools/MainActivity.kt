package com.rjasso.nycschools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rjasso.nycschools.database.NYCSchoolDB
import com.rjasso.nycschools.model.SchoolListItem

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.name

    private lateinit var viewModel: NYCSchoolsViewModel
    private lateinit var NYCSchoolRepo: NYCSchoolsRepository
    private val schoolList = mutableListOf<SchoolListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")
        setContentView(R.layout.activity_main)
        NYCSchoolRepo = NYCSchoolsRepository(NYCSchoolDB.getInstance(this))
        viewModel = ViewModelProvider(this, NYCSchoolsViewModelFactory(NYCSchoolRepo)).get(NYCSchoolsViewModel::class.java)
        viewModel.getSchools().observe(this, object: Observer<List<SchoolListItem>> {
            override fun onChanged(schools: List<SchoolListItem>?) {
                schoolList.clear()
                schools.let {
                    if (it != null) {
                        schoolList.addAll(it)
                        findViewById<TextView>(R.id.textView).text = "${schoolList.size} items"
                    }
                }
            }
        })
        viewModel.getData()
    }
}