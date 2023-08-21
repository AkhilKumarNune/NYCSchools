package com.akhil.nycschools.data.model

import com.google.gson.annotations.SerializedName

data class SATResult(@SerializedName("dbn") val dbn : String,
                     @SerializedName("school_name") val schoolName : String,
                     @SerializedName("num_of_sat_test_takers") val num_of_sat_test_takers : String,
                     @SerializedName("sat_critical_reading_avg_score") val sat_critical_reading_avg_score : String,
                     @SerializedName("sat_math_avg_score") val sat_math_avg_score : String,
                     @SerializedName("sat_writing_avg_score") val sat_writing_avg_score : String)
