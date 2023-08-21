package com.akhil.nycschools.presentation.vmf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akhil.nycschools.domain.GetSATResultsUseCase
import com.akhil.nycschools.presentation.vm.SATResultViewModel

class SATResultViewModelFactory(private val satResultsUseCase: GetSATResultsUseCase) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SATResultViewModel(satResultsUseCase) as T
    }
}