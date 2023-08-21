package com.akhil.nycschools.data.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.akhil.nycschools.data.api.MySingleton
import com.akhil.nycschools.data.model.School
import com.akhil.nycschools.domain.SchoolDataRepository
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import java.lang.Exception
import kotlin.coroutines.resume

class SchoolDataImplementation(val context : Context, val url : String) : SchoolDataRepository {
    override suspend fun getSchools(): ArrayList<School> = suspendCancellableCoroutine{continuation ->
        try {
            var response : ArrayList<School> = arrayListOf()
            CoroutineScope(Dispatchers.IO).launch {
                val stringRequest = JsonArrayRequest(Request.Method.GET, url, null, {
                    if (it != null && it.length() > 0){
                        val jsonString  = it.toString()
                        val itemType = object : TypeToken<ArrayList<School>>() {}.type
                        response = Gson().fromJson(jsonString, itemType)

                        continuation.resume(response)
                    }else{
                        Toast.makeText(context,"Problem while fetching the data", Toast.LENGTH_SHORT).show()
                    }

                }, {
                    it.printStackTrace()
                    Toast.makeText(context,"${it.message}", Toast.LENGTH_SHORT).show()
                });
                MySingleton.getInstance(context).addRequest(stringRequest)

            }

        }catch (e:  Exception){
            e.printStackTrace()
            Log.e(TAG, "getSchools: "+e.message )
        }
    }
    private  val TAG = "SchoolDataImplementatio"
}