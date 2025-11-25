package com.aliumar.mini_project

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CourseListActivity : AppCompatActivity() {

    data class Course(val title: String, val description: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_list)

        // --- HEADER FIX ---
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // ------------------

        val courses = listOf(
            Course(
                "German A-Level Preparatory Program (GAPP)",
                "A specialized 22-month program established to prepare students for engineering studies at Universities of Applied Sciences (UAS) in Germany. The curriculum combines GCE A-Levels with intensive German language training (TestDaF) and pre-study practical training (Vorpraktikum)."
            ),
            Course(
                "GMI-UTP Foundation Programme (GUFP)",
                "A one-year foundation programme offered in strategic collaboration with Universiti Teknologi PETRONAS (UTP). Using the UTP foundation syllabus, this course qualifies graduates for direct entry into undergraduate engineering, science, and technology degree programmes at UTP."
            ),
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
        listView.divider = null
        listView.dividerHeight = 0

        // Handle List Item Clicks for GAPP and GUFP
        listView.setOnItemClickListener { _, _, position, _ ->
            val course = courses[position]
            when {
                course.title.contains("GAPP") -> {
                    // Open GAPP website
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/pre-university-programme/german-a-level-preparatory-programme-gapp/"))
                    startActivity(intent)
                }
                course.title.contains("GUFP") -> {
                    // Open GUFP website
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/gmi-utp-foundation-studies-programme/"))
                    startActivity(intent)
                }
                course.title.contains("Software Engineering") -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/computer-and-information/"))
                    startActivity(intent)
                }
                course.title.contains("Network Security") -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/computer-and-information/"))
                    startActivity(intent)
                }
                course.title.contains("Mechatronics Engineering") -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/mechanical-engineering/"))
                    startActivity(intent)
                }
                course.title.contains("Product Design & Manufacturing") -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/mechanical-engineering/"))
                    startActivity(intent)
                }
                course.title.contains("Mechatronics Engineering") -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/mechanical-engineering/"))
                    startActivity(intent)
                }
                course.title.contains("CNC Precision Technology") -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my/mechanical-engineering/"))
                    startActivity(intent)
                }
            }
        }
    }

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