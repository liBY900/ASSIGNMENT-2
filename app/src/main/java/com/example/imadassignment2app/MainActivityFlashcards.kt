package com.example.imadassignment2app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivityFlashcards : AppCompatActivity() {

    private val questions = arrayOf(
        "The capital city of South Africa KZN ",
        "The first black president of America was Obama",
        "The currency of South Africa is Naira",
        "South Africa was colonized by Britain",
        "South Africa got their Independence on April 27 1994"
    )

    private val answers = arrayOf(false, true, false, true, true)
    private var score = 0
    private var currentQuestionIndex = 0

    private lateinit var questionText: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainflashcards)

        questionText = findViewById(R.id.questionText)
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        nextButton = findViewById(R.id.nextButton)

        showQuestion()

        trueButton.setOnClickListener {
            handleAnswer(true)
        }

        falseButton.setOnClickListener {
            handleAnswer(false)
        }

        nextButton.setOnClickListener {
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                showQuestion()
                enableAnswerButtons()
            } else {
                // Go to score activity
                val intent = Intent(this, MainActivityScore::class.java)
                intent.putExtra("score", score)
                startActivity(intent)
                finish() // Optional: close current activity
            }
        }
    }

    private fun showQuestion() {
        questionText.text = questions[currentQuestionIndex]
    }

    private fun handleAnswer(userAnswer: Boolean) {
        val correctAnswer = answers[currentQuestionIndex]
        if (userAnswer == correctAnswer) {
            score++
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
        }
        disableAnswerButtons()
    }

    private fun disableAnswerButtons() {
        trueButton.isEnabled = false
        falseButton.isEnabled = false
    }

    private fun enableAnswerButtons() {
        trueButton.isEnabled = true
        falseButton.isEnabled = true
    }
}




