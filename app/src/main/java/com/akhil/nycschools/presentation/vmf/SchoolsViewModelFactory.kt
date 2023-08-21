package com.akhil.nycschools.presentation.vmf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akhil.nycschools.domain.GetSchoolsUserCase
import com.akhil.nycschools.presentation.vm.SchoolsViewModel

class SchoolsViewModelFactory(private val schoolsUserCase: GetSchoolsUserCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SchoolsViewModel(schoolsUserCase) as T
    }

}