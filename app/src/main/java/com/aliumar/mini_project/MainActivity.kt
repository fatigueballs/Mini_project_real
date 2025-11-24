package com.aliumar.mini_project

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.widget.Button
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

        // 4. About Us Button Logic (Popup)
        val btnAbout = findViewById<LinearLayout>(R.id.btn_about)
        btnAbout.setOnClickListener {
            showAboutPopup()
        }
    }

    private fun showAboutPopup() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.popup_about_us)

        // Make background transparent so the card corners show properly
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Set dialog width to match parent with some margin (optional tuning)
        dialog.window?.setLayout(
            android.view.ViewGroup.LayoutParams.MATCH_PARENT,
            android.view.ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val btnClose = dialog.findViewById<Button>(R.id.btnClosePopup)
        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}