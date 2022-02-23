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
        val button: Button = findViewById(R.id.add_button)
        button.setOnClickListener {
            val i = Intent(this@GameSelection, GameScreen::class.java)
            startActivity(i)
        }
    }
}