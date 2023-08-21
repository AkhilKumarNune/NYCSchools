package com.akhil.nycschools.domain

import com.akhil.nycschools.data.model.School

interface SchoolDataRepository {
    suspend fun getSchools() : ArrayList<School>
}