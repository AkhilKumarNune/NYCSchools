package com.akhil.nycschools.presentation.vm

import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akhil.nycschools.data.model.School
import com.akhil.nycschools.domain.GetSchoolsUserCase
import kotlinx.coroutines.launch

class SchoolsViewModel(private val schoolsUserCase: GetSchoolsUserCase) : ViewModel() {

    private val schoolsApiResult = MutableLiveData<ArrayList<School>>()

    val schoolsApiLiveData : LiveData<ArrayList<School>> = schoolsApiResult

    fun fetchSchoolsData(){
        viewModelScope.launch {
            val results = schoolsUserCase.execute()
            schoolsApiResult.value = results
        }
    }

}