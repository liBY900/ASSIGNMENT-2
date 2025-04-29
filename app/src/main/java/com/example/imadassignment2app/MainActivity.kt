package com.example.imadassignment2app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton: Button = findViewById(R.id.startbutton)

        startButton.setOnClickListener {
            // Navigate to flashcard Question screen
            val intent = Intent(this, MainActivityFlashcards::class.java)
            startActivity(intent)
        }
    }
}
