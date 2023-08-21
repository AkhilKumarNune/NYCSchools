package com.akhil.nycschools.domain

import com.akhil.nycschools.data.model.SATResult

class GetSATResultsUseCase(private val satResultDataRepository: SATResultDataRepository) {
    suspend fun execute() : ArrayList<SATResult>{
        return satResultDataRepository.getSATResults()
    }
}