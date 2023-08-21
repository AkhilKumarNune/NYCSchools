package com.akhil.nycschools.data.repository

import android.content.Context
import android.widget.Toast
import com.akhil.nycschools.data.api.MySingleton
import com.akhil.nycschools.data.model.SATResult
import com.akhil.nycschools.domain.SATResultDataRepository
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class SATResultDataImplementation(val context: Context, val url : String) : SATResultDataRepository {
    override suspend fun getSATResults(): ArrayList<SATResult> = suspendCancellableCoroutine { continuation ->
        var satResultList = arrayListOf<SATResult>()
        CoroutineScope(Dispatchers.IO).launch{
            val stringRequest = JsonArrayRequest(Request.Method.GET, url, null, {
                if (it != null){
                    val jsonString = it.toString()
                    val itemList = object : TypeToken<ArrayList<SATResult>>(){}.type
                    satResultList = Gson().fromJson(jsonString, itemList)
                    continuation.resume(satResultList)
                }else{
                    Toast.makeText(context, "Error in fetching results", Toast.LENGTH_SHORT).show()
                }
            }, {
                Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
            })
            MySingleton.getInstance(context).addRequest(stringRequest)

        }

    }
}