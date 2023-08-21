package com.akhil.nycschools.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.akhil.nycschools.R
import com.akhil.nycschools.data.model.School

class SchoolsAdapter(private val clickedItem : (School) -> Unit) : Adapter<SchoolsAdapter.SchoolViewHolder>() {
    var schoolList : ArrayList<School> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.school_item, parent, false)
        return SchoolViewHolder(view)
    }

    override fun getItemCount(): Int {
        return schoolList.size
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        val school = schoolList[position]
        holder.school_name.text = school.schoolName
        holder.phone_tv.text = school.phoneNumber
        holder.location.text = "${school.primary_address_line_1}, ${school.city}, ${school.zip}"
        holder.website.text = school.website
        holder.email.text = school.schoolEmail

        holder.itemView.setOnClickListener{
            clickedItem(school)
        }
    }

    class SchoolViewHolder(itemView : View) : ViewHolder(itemView){
        val school_name : TextView
        val students : TextView
        val website : TextView
        val location : TextView
        val email : TextView
        val phone_tv : TextView

        init {
            school_name = itemView.findViewById(R.id.school_name_tv)
            students = itemView.findViewById(R.id.students_tv)
            website = itemView.findViewById(R.id.website_tv)
            location = itemView.findViewById(R.id.location_tv)
            email = itemView.findViewById(R.id.email_tv)
            phone_tv = itemView.findViewById(R.id.phone_tv)

        }

    }

    fun updateSchools(list : ArrayList<School>){
        schoolList.clear()
        schoolList.addAll(list)
        notifyDataSetChanged()
    }

}