package com.aliumar.mini_project

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EligibilityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eligibility)

        // Edge-to-edge padding fix
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize UI elements
        val spinnerMath = findViewById<Spinner>(R.id.spinnerMath)
        val spinnerScience = findViewById<Spinner>(R.id.spinnerScience)
        val spinnerEnglish = findViewById<Spinner>(R.id.spinnerEnglish)
        val spinnerOther = findViewById<Spinner>(R.id.spinnerOther)
        val spinnerOther2 = findViewById<Spinner>(R.id.spinnerOther2) // New spinner
        val tvOther2 = findViewById<TextView>(R.id.tvOther2)

        val btnCheck = findViewById<Button>(R.id.btnCheck)
        val txtResult = findViewById<TextView>(R.id.txtResult)
        val btnApply = findViewById<Button>(R.id.btnApply)

        val rgProgramType = findViewById<RadioGroup>(R.id.rgProgramType)
        val rbDiploma = findViewById<RadioButton>(R.id.rbDiploma)

        // Define grades list
        val grades = listOf("A+", "A", "A-", "B+", "B", "C+", "C", "D", "E", "G")

        // Create adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, grades)

        // Apply adapter to all spinners
        spinnerMath.adapter = adapter
        spinnerScience.adapter = adapter
        spinnerEnglish.adapter = adapter
        spinnerOther.adapter = adapter
        spinnerOther2.adapter = adapter

        // Toggle visibility of the 5th subject field based on program selection
        rgProgramType.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.rbPreU) {
                spinnerOther2.visibility = View.VISIBLE
                tvOther2.visibility = View.VISIBLE
            } else {
                spinnerOther2.visibility = View.GONE
                tvOther2.visibility = View.GONE
            }
            // Reset result when switching
            txtResult.text = ""
            btnApply.visibility = View.GONE
        }

        btnCheck.setOnClickListener {
            // Get selected items from spinners
            val math = getGradeValue(spinnerMath.selectedItem.toString())
            val science = getGradeValue(spinnerScience.selectedItem.toString())
            val english = getGradeValue(spinnerEnglish.selectedItem.toString())
            val other = getGradeValue(spinnerOther.selectedItem.toString())
            val other2 = getGradeValue(spinnerOther2.selectedItem.toString())

            val isDiploma = rbDiploma.isChecked
            var isEligible = false

            if (isDiploma) {
                // Diploma Logic:
                // 1. Math <= 3 (Credit)
                // 2. Science <= 3 (Credit)
                // 3. 3rd Subject <= 3 (Credit)
                // 4. English <= 5 (Pass)
                if (math <= 3 && science <= 3 && other <= 3 && english <= 5) {
                    isEligible = true
                }
            } else {
                // Pre-U (GAPP/GUFP) Logic:
                // 1. Total 5 Credits
                // Using: Math, Science, English, 3rd Subj, 4th Subj
                // All must be <= 3 (Credit)
                if (math <= 3 && science <= 3 && english <= 3 && other <= 3 && other2 <= 3) {
                    isEligible = true
                }
            }

            if (isEligible) {
                txtResult.text = "Congratulations! You are ELIGIBLE."
                txtResult.setTextColor(Color.parseColor("#008000")) // Green
                btnApply.visibility = View.VISIBLE
            } else {
                txtResult.text = "We are sorry, you do not meet the minimum requirements."
                txtResult.setTextColor(Color.RED)
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