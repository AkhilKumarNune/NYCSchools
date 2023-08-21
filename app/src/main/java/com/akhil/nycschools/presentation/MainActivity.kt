package com.akhil.nycschools.presentation

import android.app.Dialog
import android.content.res.Resources.Theme
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akhil.nycschools.R
import com.akhil.nycschools.data.model.SATResult
import com.akhil.nycschools.data.model.School
import com.akhil.nycschools.data.repository.SATResultDataImplementation
import com.akhil.nycschools.data.repository.SchoolDataImplementation
import com.akhil.nycschools.domain.GetSATResultsUseCase
import com.akhil.nycschools.domain.GetSchoolsUserCase
import com.akhil.nycschools.presentation.adapters.SchoolsAdapter
import com.akhil.nycschools.presentation.vm.SATResultViewModel
import com.akhil.nycschools.presentation.vm.SchoolsViewModel
import com.akhil.nycschools.presentation.vmf.SATResultViewModelFactory
import com.akhil.nycschools.presentation.vmf.SchoolsViewModelFactory

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var adapter : SchoolsAdapter
    private lateinit var satResultViewModel : SATResultViewModel
    private lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val schoolDataImplementation = SchoolDataImplementation(this, " https://data.cityofnewyork.us/resource/s3k6-pzi2.json")
        val schoolsUserCase = GetSchoolsUserCase(schoolDataImplementation)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SchoolsAdapter(){school ->
            fetchSATResults(school)
        }
        recyclerView.adapter = adapter

        val schoolsViewModel = ViewModelProvider(this, SchoolsViewModelFactory(schoolsUserCase)).get(SchoolsViewModel::class.java)

        schoolsViewModel.schoolsApiLiveData.observe(this){
            adapter.updateSchools(it)
            progressBar.visibility = View.GONE
        }

        schoolsViewModel.fetchSchoolsData()
        progressBar.visibility = View.VISIBLE

    }

    private fun fetchSATResults(school: School) {
        progressBar.visibility = View.VISIBLE
        val url = "https://data.cityofnewyork.us/resource/f9bf-2cp4?dbn=${school.dbn}"
        val satResultDataImplementation = SATResultDataImplementation(this@MainActivity, url)
        val satResultUserCase = GetSATResultsUseCase(satResultDataImplementation)
        satResultViewModel = ViewModelProvider(this, SATResultViewModelFactory(satResultUserCase)).get(SATResultViewModel::class.java)

        satResultViewModel.satResultApiLiveData.observe(this){
            progressBar.visibility = View.GONE
//            displayDialog(it[0])
            Toast.makeText(this,"Unimplemented due to Api Mismatch", Toast.LENGTH_SHORT).show()
        }

        satResultViewModel.fetchSATResultData()
    }

    private fun displayDialog(satResult: SATResult) {
        val dialog = Dialog(this, R.style.CustomDialogStyle)
        dialog.setContentView(R.layout.sat_result_dialog_layout)
        dialog.setCancelable(true)

        val num_of_sat_test_takers = dialog.findViewById<TextView>(R.id.num_of_sat_test_takers)
        val sat_math_avg_score = dialog.findViewById<TextView>(R.id.sat_math_avg_score)
        val sat_writing_avg_score = dialog.findViewById<TextView>(R.id.sat_writing_avg_score)

        num_of_sat_test_takers.text = satResult.num_of_sat_test_takers
        sat_math_avg_score.text = satResult.sat_math_avg_score
        sat_writing_avg_score.text = satResult.sat_writing_avg_score

        dialog.show()
    }
}