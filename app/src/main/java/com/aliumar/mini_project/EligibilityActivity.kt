package com.aliumar.mini_project

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EligibilityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eligibility)

        val inputMath = findViewById<EditText>(R.id.inputMath)
        val inputScience = findViewById<EditText>(R.id.inputScience)
        val inputEnglish = findViewById<EditText>(R.id.inputEnglish)
        val inputOther = findViewById<EditText>(R.id.inputOther)
        val btnCheck = findViewById<Button>(R.id.btnCheck)
        val txtResult = findViewById<TextView>(R.id.txtResult)

        btnCheck.setOnClickListener {
            val math = getGradeValue(inputMath.text.toString())
            val science = getGradeValue(inputScience.text.toString())
            val english = getGradeValue(inputEnglish.text.toString())
            val other = getGradeValue(inputOther.text.toString())

            // Logic:
            // 1. Math Must be Credit (A, B, C) -> value <= 3
            // 2. Science Must be Credit (A, B, C) -> value <= 3
            // 3. Other Must be Credit (A, B, C) -> value <= 3
            // 4. English Must be Pass (A, B, C, D, E) -> value <= 5

            if (math <= 3 && science <= 3 && other <= 3 && english <= 5) {
                txtResult.text = "Congratulations! You are ELIGIBLE."
                txtResult.setTextColor(Color.parseColor("#008000")) // Green
            } else {
                txtResult.text = "We are sorry, you do not meet the minimum requirements."
                txtResult.setTextColor(Color.RED)
            }
        }
    }

    // Helper to convert grade char to comparable int (A=1, B=2, C=3, D=4, E=5, F=6)
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