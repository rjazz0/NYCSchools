package com.rjasso.nycschools.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rjasso.nycschools.model.SchoolListItem

@Dao
interface schoolsDAO {
    @Insert
    fun insert(school: SchoolListItem)

    @Delete
    fun delete(school: SchoolListItem)

    @Query("DELETE FROM NYCSchools")
    fun deleteAll()

    @Insert
    fun insertAll(school: List<SchoolListItem>)

    @Query("SELECT * FROM NYCSchools")
    fun getAll(): List<SchoolListItem>
}
