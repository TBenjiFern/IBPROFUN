package com.example.ibprofun

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class GameScreen: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_screen)
        val button: Button = findViewById(R.id.return_game_select)
        button.setOnClickListener {
            val i = Intent(this@GameScreen, GameSelection::class.java)
            startActivity(i)
        }
    }
}