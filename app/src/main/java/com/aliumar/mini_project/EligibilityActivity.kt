package com.aliumar.mini_project

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
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

        val inputMath = findViewById<EditText>(R.id.inputMath)
        val inputScience = findViewById<EditText>(R.id.inputScience)
        val inputEnglish = findViewById<EditText>(R.id.inputEnglish)
        val inputOther = findViewById<EditText>(R.id.inputOther)
        val btnCheck = findViewById<Button>(R.id.btnCheck)
        val txtResult = findViewById<TextView>(R.id.txtResult)
        val btnApply = findViewById<Button>(R.id.btnApply)

        btnCheck.setOnClickListener {
            val math = getGradeValue(inputMath.text.toString())
            val science = getGradeValue(inputScience.text.toString())
            val english = getGradeValue(inputEnglish.text.toString())
            val other = getGradeValue(inputOther.text.toString())

            // Logic:
            // 1. Math <= 3 (Credit)
            // 2. Science <= 3 (Credit)
            // 3. Other <= 3 (Credit)
            // 4. English <= 5 (Pass)

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
        val g = grade.trim().uppercase()
        return when {
            g.startsWith("A") -> 1
            g.startsWith("B") -> 2
            g.startsWith("C") -> 3
            g.startsWith("D") -> 4
            g.startsWith("E") -> 5
            else -> 6 // F or invalid
        }
    }
}