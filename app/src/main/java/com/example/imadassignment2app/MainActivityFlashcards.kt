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

    private val answers = booleanArrayOf(false, true, false, true, true)
    private var userAnswers = BooleanArray(questions.size) // Store user answers
    private var score = 0
    private var currentQuestionIndex = 0

    private lateinit var questionText: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainflashcards)

        // Initialize the class properties (not local variables!)
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
                // Send all data to MainActivityScore
                val intent = Intent(this, MainActivityScore::class.java)
                intent.putExtra("questions", questions)
                intent.putExtra("answers", answers)
                intent.putExtra("userAnswers", userAnswers)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun showQuestion() {
        questionText.text = questions[currentQuestionIndex]
    }

    private fun handleAnswer(userAnswer: Boolean) {
        userAnswers[currentQuestionIndex] = userAnswer // Store user answer
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
