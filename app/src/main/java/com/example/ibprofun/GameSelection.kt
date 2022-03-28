package com.example.ibprofun

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class GameSelection : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_selection)
        val backButton: Button = findViewById(R.id.backHome)
        val addButton: Button = findViewById(R.id.additionButton)
        val subButton: Button = findViewById(R.id.subtractionButton)
        val multButton: Button = findViewById(R.id.multiplicationButton)
        val divButton: Button = findViewById(R.id.divisionButton)
        backButton.setOnClickListener {
            val i = Intent(this@GameSelection, MainActivity::class.java)
            startActivity(i)
        }
        addButton.setOnClickListener {
            val i = Intent(this@GameSelection, GameScreen::class.java)
            i.putExtra("mode","addition")
            startActivity(i)
        }
        subButton.setOnClickListener {
            val i = Intent(this@GameSelection, GameScreen::class.java)
            i.putExtra("mode","subtraction")
            startActivity(i)
        }
        multButton.setOnClickListener {
            val i = Intent(this@GameSelection, GameScreen::class.java)
            i.putExtra("mode","multiplication")
            startActivity(i)
        }
        divButton.setOnClickListener {
            val i = Intent(this@GameSelection, GameScreen::class.java)
            i.putExtra("mode","division")
            startActivity(i)
        }
    }
}