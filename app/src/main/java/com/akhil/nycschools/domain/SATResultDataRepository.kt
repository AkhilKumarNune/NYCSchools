package com.akhil.nycschools.domain

import com.akhil.nycschools.data.model.SATResult

interface SATResultDataRepository {
    suspend fun getSATResults() : ArrayList<SATResult>
}