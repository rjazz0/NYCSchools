package com.rjasso.nycschools

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NYCSchoolsViewModelFactory(val NYCSchoolsRepo: NYCSchoolsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NYCSchoolsViewModel(NYCSchoolsRepo) as T
    }
}