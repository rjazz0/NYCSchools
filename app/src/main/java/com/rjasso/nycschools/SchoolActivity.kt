package com.rjasso.nycschools

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rjasso.nycschools.model.SchoolSATItem

class SchoolActivity : AppCompatActivity() {
    lateinit var school: SchoolSATItem
    companion object{
        val SCHOOL_OBJECT = "school_object"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school)
        school = intent.getSerializableExtra(SCHOOL_OBJECT) as SchoolSATItem
    }

    override fun onResume() {
        super.onResume()
        findViewById<TextView>(R.id.school_sat_name).text = school.school_name
        findViewById<TextView>(R.id.readingTextView).text = "Reading: ${school.sat_critical_reading_avg_score}"
        findViewById<TextView>(R.id.mathTextView).text = "Math: ${school.sat_math_avg_score}"
        findViewById<TextView>(R.id.writingTextView).text = "Writing: ${school.sat_writing_avg_score}"
    }
}