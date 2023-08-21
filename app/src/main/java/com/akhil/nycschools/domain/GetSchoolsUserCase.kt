package com.akhil.nycschools.domain

import com.akhil.nycschools.data.model.School

class GetSchoolsUserCase (private val schoolDataRepository: SchoolDataRepository) {
    suspend fun execute() : ArrayList<School>{
        return schoolDataRepository.getSchools()
    }
}