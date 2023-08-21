package com.akhil.nycschools.data.api

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class MySingleton constructor(context : Context) {

    companion object{
        @Volatile
        private var MY_INSTANCE : MySingleton? = null
        fun getInstance(context : Context)= MY_INSTANCE ?: synchronized(this){
            MY_INSTANCE ?: MySingleton(context).also {
                MY_INSTANCE = it
            }
        }
    }

    val requestQueue : RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> addRequest(request : Request<T>){
        requestQueue.add(request)
    }

}