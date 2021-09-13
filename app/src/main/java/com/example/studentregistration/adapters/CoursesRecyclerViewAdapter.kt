package com.example.studentregistration.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentregistration.R
import com.example.studentregistration.models.CoursesResponse
import com.example.studentregistration.ui.CoursesActivity
import com.example.studentregistration.ui.EnrolmentClickListener

class CoursesRecyclerViewAdapter(var courseList:List<CoursesResponse>,var context: Context, var enrolmentClickListener:
EnrolmentClickListener): RecyclerView
.Adapter<CoursesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.course_item_view,parent,false)
        return CoursesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        var currentCourse = courseList.get(position)
        holder.tvCourseName.text = currentCourse.course_name
        holder.tvDescription.text = currentCourse.description
        holder.tvInstructor.text = currentCourse.instructor
        holder.tvCode.text =  currentCourse.course_code

        holder.btnEnrol.setOnClickListener {
            enrolmentClickListener.onClickEnrolment(currentCourse.course_id)
        }

    }

    override fun getItemCount(): Int {
        return  courseList.size
    }
}
class CoursesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    var tvCourseName = itemView.findViewById<TextView>(R.id.tvCourseName)
    var tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
    var tvInstructor = itemView.findViewById<TextView>(R.id.tvInstructor)
    var tvCode = itemView.findViewById<TextView>(R.id.tvCode)
    var btnEnrol = itemView.findViewById<ImageView>(R.id.btnEnrol)

}