package com.example.imadassignment2app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivityScore : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        // Receive data from Intent
        val questions = intent.getStringArrayExtra("questions") ?: arrayOf()
        val correctAnswers = intent.getBooleanArrayExtra("answers") ?: booleanArrayOf()
        val userAnswers = intent.getBooleanArrayExtra("userAnswers") ?: booleanArrayOf()

        val scoreText = findViewById<TextView>(R.id.score)
        val feedbackText = findViewById<TextView>(R.id.feedback)
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val reviewButton = findViewById<Button>(R.id.button2)
        val exitButton = findViewById<Button>(R.id.button)

        // Calculate the score
        var calculatedScore = 0
        for (i in questions.indices) {
            if (userAnswers.getOrNull(i) == correctAnswers.getOrNull(i)) {
                calculatedScore++
            }
        }

        // Display score
        scoreText.text = "You got $calculatedScore out of ${questions.size} correct"

        // Show stars
        ratingBar.rating = calculatedScore.toFloat()

        // Show feedback
        val feedback = when (calculatedScore) {
            5 -> "Well done! Perfect score!"
            in 3..4 -> "Well done! Just some mistakes."
            in 1..2 -> "Keep trying, you’re almost there."
            else -> "You might want to review the material!"
        }
        feedbackText.text = feedback

        // Exit
        exitButton.setOnClickListener {
            finishAffinity()
        }

        // Go to review screen
        reviewButton.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            intent.putExtra("questions", questions)
            intent.putExtra("answers", correctAnswers)
            intent.putExtra("userAnswers", userAnswers)
            startActivity(intent)
        }
    }
}
