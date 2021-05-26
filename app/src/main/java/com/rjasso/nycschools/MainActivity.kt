package com.rjasso.nycschools

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rjasso.nycschools.database.NYCSchoolDB
import com.rjasso.nycschools.model.SchoolListItem
import com.rjasso.nycschools.model.SchoolSATItem


class MainActivity : AppCompatActivity(), SchoolCallback {
    val TAG = MainActivity::class.java.name

    private lateinit var viewModel: NYCSchoolsViewModel
    private lateinit var NYCSchoolRepo: NYCSchoolsRepository
    private lateinit var schoolRecyclerView: RecyclerView
    private val schoolList = mutableListOf<SchoolListItem>()
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")
        setContentView(R.layout.activity_main)
        schoolRecyclerView = findViewById(R.id.schoolRecyclerView)
        schoolRecyclerView.adapter = SchoolAdapter(schoolList, this)
        schoolRecyclerView.layoutManager = LinearLayoutManager(this)
        NYCSchoolRepo = NYCSchoolsRepository(NYCSchoolDB.getInstance(this))
        progressBar = findViewById(R.id.progress_dialog)
        progressBar.visibility = View.VISIBLE
        viewModel = ViewModelProvider(this, NYCSchoolsViewModelFactory(NYCSchoolRepo)).get(NYCSchoolsViewModel::class.java)
        viewModel.getSchools().observe(this, object: Observer<List<SchoolListItem>> {
            override fun onChanged(schools: List<SchoolListItem>?) {
                schoolList.clear()
                schools.let {
                    if (it != null) {
                        schoolList.addAll(it)
                        schoolRecyclerView?.adapter?.notifyDataSetChanged()
                        if (it.size > 0) {
                            progressBar.visibility = View.GONE
                        }
                    }
                }
            }
        })
        viewModel.getSATData().observe(this, object: Observer<SchoolSATItem>{
            override fun onChanged(item: SchoolSATItem?) {
                val schoolActivityIntent = Intent(applicationContext, SchoolActivity::class.java)
                schoolActivityIntent.putExtra(SchoolActivity.SCHOOL_OBJECT, item)
                startActivity(schoolActivityIntent)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        progressBar.visibility = View.VISIBLE
        viewModel.getData()
    }

    override fun callSchoolSATService(dbn: String) {
        viewModel.getSATDataObject(dbn)
    }
}