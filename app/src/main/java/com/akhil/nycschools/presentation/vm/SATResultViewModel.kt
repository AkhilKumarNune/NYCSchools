package com.akhil.nycschools.presentation.vm

import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akhil.nycschools.data.model.SATResult
import com.akhil.nycschools.domain.GetSATResultsUseCase
import kotlinx.coroutines.launch

class SATResultViewModel(private val satResultsUseCase: GetSATResultsUseCase) : ViewModel(){

    private val satResultsApiResult = MutableLiveData<ArrayList<SATResult>>()

    val satResultApiLiveData : LiveData<ArrayList<SATResult>> = satResultsApiResult

    fun fetchSATResultData(){
        viewModelScope.launch {
            val results = satResultsUseCase.execute()
            satResultsApiResult.value = results
        }
    }
}