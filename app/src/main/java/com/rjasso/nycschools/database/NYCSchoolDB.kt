package com.rjasso.nycschools.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rjasso.nycschools.model.SchoolListItem

@Database(entities = [SchoolListItem::class], version = 1, exportSchema = false)
abstract class NYCSchoolDB: RoomDatabase() {
    companion object {
        val DATABASE_NAME = "NYCSchoolDB"
        private var database: NYCSchoolDB? = null

        @Synchronized
        fun getInstance(context: Context): NYCSchoolDB {
            if (database == null) {
                database = Room.databaseBuilder(context.applicationContext, NYCSchoolDB::class.java, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return database as NYCSchoolDB
        }
    }
    abstract fun schoolsDAO(): schoolsDAO
}