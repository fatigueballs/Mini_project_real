package com.aliumar.mini_project

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CourseListActivity : AppCompatActivity() {

    data class Course(val title: String, val description: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_list)

        // Detailed course data based on GMI's official offerings
        val courses = listOf(
            Course(
                "Diploma in Software Engineering",
                "Designed to develop competencies in the software development process, involving defect prevention and detection strategies to reduce risks and costs. Students will master software modelling, Android programming, and functional testing methods to meet global industry demands."
            ),
            Course(
                "Diploma in Network Security",
                "Focuses on the security of overall network architecture, including designing secure networks, configuring security tools, and managing databases. Students learn to protect data and manage personnel conduct based on evolving industry standards and regulations."
            ),
            Course(
                "Diploma in Mechatronics Engineering",
                "A unique synergistic integration of mechanical engineering, electronics, and intelligent computer control. This multidisciplinary course addresses the challenges of designing smart technologies and manufacturing intelligent systems for Industry 4.0."
            ),
            Course(
                "Diploma in Process Instrumentation & Control",
                "Provides sound theoretical and practical training in the operation and maintenance of automated process control and measurement systems. Students learn to use PLC and Distributed Control Systems (DCS) to monitor industrial variables like flow, temperature, and pressure."
            ),
            Course(
                "Diploma in Product Design & Manufacturing",
                "Offers a thorough understanding of design principles and manufacturing technologies. The curriculum covers the entire product lifecycle, from identifying market opportunities to design, rapid prototyping, and final manufacturing."
            ),
            Course(
                "Diploma in CNC Precision Technology",
                "Focuses on high-precision manufacturing skills, including CNC machining, tool & die making, and computer-aided manufacturing (CAM). Students are trained to produce complex components with tight tolerances required by modern engineering industries."
            )
        )

        val listView: ListView = findViewById(R.id.courseListView)
        val adapter = CourseAdapter(this, courses)
        listView.adapter = adapter

        // Remove default dividers since we are using cards with margins
        listView.divider = null
        listView.dividerHeight = 0
    }

    // Custom Adapter to handle the card layout
    inner class CourseAdapter(context: Context, private val dataSource: List<Course>) :
        ArrayAdapter<Course>(context, R.layout.item_course, dataSource) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_course, parent, false)

            val course = dataSource[position]

            val titleView = view.findViewById<TextView>(R.id.courseTitle)
            val descView = view.findViewById<TextView>(R.id.courseDescription)

            titleView.text = course.title
            descView.text = course.description

            return view
        }
    }
}