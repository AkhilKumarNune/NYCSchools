package com.akhil.nycschools.data.model

import com.google.gson.annotations.SerializedName

data class School(@SerializedName("dbn") val dbn : String,
                  @SerializedName("school_name") val schoolName : String,
                  @SerializedName("phone_number") val phoneNumber : String,
                  @SerializedName("school_email") val schoolEmail : String,
                  @SerializedName("website") val website : String,
                  @SerializedName("total_students") val totalStudents : String,
                  @SerializedName("primary_address_line_1") val primary_address_line_1 : String,
                  @SerializedName("city") val city : String,
                  @SerializedName("zip") val zip : String,
                  @SerializedName("state_code") val stateCode : String
)
