package com.example.imadassignment2app

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        val reviewText: TextView = findViewById(R.id.reviewtext)
        val exitButton: Button = findViewById(R.id.exitbutton3)

        // Get data
        val questions = intent.getStringArrayExtra("questions") ?: emptyArray()
        val answers = intent.getBooleanArrayExtra("answers") ?: booleanArrayOf()
        val userAnswers = intent.getBooleanArrayExtra("userAnswers") ?: booleanArrayOf()

        // Validate data length
        val itemCount = minOf(questions.size, answers.size, userAnswers.size)

        if (itemCount == 0) {
            reviewText.text = "No review data available."
        } else {
            val reviewStringBuilder = StringBuilder()
            for (i in 0 until itemCount) {
                reviewStringBuilder.append("Q: ${questions[i]}\n")
                reviewStringBuilder.append("Your Answer: ${if (userAnswers[i] == answers[i]) "✅ Correct" else "❌ Incorrect"}\n")
                reviewStringBuilder.append("Correct Answer: ${if (answers[i]) "True" else "False"}\n\n")
            }
            reviewText.text = reviewStringBuilder.toString()
        }

        // Exit
        exitButton.setOnClickListener {
            finishAffinity()
        }
    }
}
