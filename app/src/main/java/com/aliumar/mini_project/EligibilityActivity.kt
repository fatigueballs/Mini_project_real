package com.aliumar.mini_project

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EligibilityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_eligibility)

        // Edge-to-edge padding fix
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize UI elements (Spinners instead of EditText)
        val spinnerMath = findViewById<Spinner>(R.id.spinnerMath)
        val spinnerScience = findViewById<Spinner>(R.id.spinnerScience)
        val spinnerEnglish = findViewById<Spinner>(R.id.spinnerEnglish)
        val spinnerOther = findViewById<Spinner>(R.id.spinnerOther)

        val btnCheck = findViewById<Button>(R.id.btnCheck)
        val txtResult = findViewById<TextView>(R.id.txtResult)
        val btnApply = findViewById<Button>(R.id.btnApply)

        // define grades list
        val grades = listOf("A+", "A", "A-", "B+", "B", "C+", "C", "D", "E", "G")

        // Create adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, grades)

        // Apply adapter to all spinners
        spinnerMath.adapter = adapter
        spinnerScience.adapter = adapter
        spinnerEnglish.adapter = adapter
        spinnerOther.adapter = adapter

        btnCheck.setOnClickListener {
            // Get selected items from spinners
            val math = getGradeValue(spinnerMath.selectedItem.toString())
            val science = getGradeValue(spinnerScience.selectedItem.toString())
            val english = getGradeValue(spinnerEnglish.selectedItem.toString())
            val other = getGradeValue(spinnerOther.selectedItem.toString())

            // Logic:
            // 1. Math <= 3 (Credit: A to C)
            // 2. Science <= 3 (Credit: A to C)
            // 3. Other <= 3 (Credit: A to C)
            // 4. English <= 5 (Pass: A to E)

            // Note: In this logic, Credit implies C (value 3) or better.
            if (math <= 3 && science <= 3 && other <= 3 && english <= 5) {
                txtResult.text = "Congratulations! You are ELIGIBLE."
                txtResult.setTextColor(Color.parseColor("#008000")) // Green

                // Show the Apply button
                btnApply.visibility = View.VISIBLE
            } else {
                txtResult.text = "We are sorry, you do not meet the minimum requirements."
                txtResult.setTextColor(Color.RED)

                // Hide the Apply button if they re-check and fail
                btnApply.visibility = View.GONE
            }
        }

        // Apply Button Logic
        btnApply.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://gmi.vialing.com/oa/login"))
            startActivity(browserIntent)
        }
    }

    private fun getGradeValue(grade: String): Int {
        // Simplified logic since input is now controlled by Spinner
        return when {
            grade.startsWith("A") -> 1 // Covers A+, A, A-
            grade.startsWith("B") -> 2 // Covers B+, B
            grade.startsWith("C") -> 3 // Covers C+, C
            grade.startsWith("D") -> 4
            grade.startsWith("E") -> 5
            else -> 6 // G or Fail
        }
    }
}