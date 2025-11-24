package com.aliumar.mini_project

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Handle Window Insets for edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Website Button Logic
        val btnWebsite = findViewById<LinearLayout>(R.id.btn_website)
        btnWebsite.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my"))
            startActivity(browserIntent)
        }

        // 2. Course List Button Logic
        val btnCourses = findViewById<LinearLayout>(R.id.btn_courses)
        btnCourses.setOnClickListener {
            val intent = Intent(this, CourseListActivity::class.java)
            startActivity(intent)
        }

        // 3. Eligibility Button Logic
        val btnEligibility = findViewById<LinearLayout>(R.id.btn_eligibility)
        btnEligibility.setOnClickListener {
            val intent = Intent(this, EligibilityActivity::class.java)
            startActivity(intent)
        }
    }
}