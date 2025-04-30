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

        // Get data passed from FlashcardActivity
        val questions = intent.getStringArrayExtra("questions") ?: arrayOf()
        val answers = intent.getBooleanArrayExtra("answers") ?: booleanArrayOf()
        val userAnswers = intent.getBooleanArrayExtra("userAnswers") ?: booleanArrayOf()

        // Build a review text to show questions and answers
        val reviewStringBuilder = StringBuilder()
        for (i in questions.indices) {
            reviewStringBuilder.append("Q: ${questions[i]}\n")
            reviewStringBuilder.append("Your Answer: ${if (userAnswers[i] == answers[i]) "Correct" else "Incorrect"}\n")
            reviewStringBuilder.append("Correct Answer: ${if (answers[i]) "True" else "False"}\n\n")
        }

        // Display the review content
        reviewText.text = reviewStringBuilder.toString()

        val exitButton: Button = findViewById(R.id.exitbutton3)
        exitButton.setOnClickListener {
            finishAffinity()
        }
    }
}
