package com.rjasso.nycschools

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rjasso.nycschools.model.SchoolListItem

class SchoolAdapter(val schoolList: MutableList<SchoolListItem>) : RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
         return SchoolViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.school_item, parent, false))
    }

    override fun getItemCount(): Int {
        return schoolList.size
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        val school = schoolList[position]
        holder.itemView.findViewById<TextView>(R.id.school_name).text = school.school_name
        holder.itemView.findViewById<TextView>(R.id.interest).text = school.interest1
        holder.itemView.findViewById<TextView>(R.id.location).text = school.location
        holder.itemView.findViewById<TextView>(R.id.phone_number).text = school.phone_number
    }

    class SchoolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
