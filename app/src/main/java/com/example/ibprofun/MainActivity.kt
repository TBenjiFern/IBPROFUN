package com.example.ibprofun

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro_screen)
        val start:Button = findViewById(R.id.startLearningButton)
        start.setOnClickListener {
            val intent = Intent(this@MainActivity, GameSelection::class.java)
            startActivity(intent)
        }
        val setting:Button = findViewById(R.id.gameSettingsButton)
        setting.setOnClickListener {
            val intent = Intent(this@MainActivity, GameSettings::class.java)
            startActivity(intent)
        }
        val score:Button = findViewById(R.id.HighScoreButton)
        score.setOnClickListener {
            val intent = Intent(this@MainActivity, HighScoresScreen::class.java)
            startActivity(intent)
        }
    }
}
