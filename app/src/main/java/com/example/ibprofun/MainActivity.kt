package com.example.ibprofun

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // This is the main page of the application which houses three button to redirect to the rest of the app
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This displays the intro_screen view
        setContentView(R.layout.intro_screen)
        // Creates the start button which has an onclicklistener to change pages
        val start:Button = findViewById(R.id.startLearningButton)
        start.setOnClickListener {
            val intent = Intent(this@MainActivity, GameSelection::class.java)
            startActivity(intent)
        }
        // Creates the game settings button which has an onclicklistener to change pages
        val setting:Button = findViewById(R.id.gameSettingsButton)
        setting.setOnClickListener {
            val intent = Intent(this@MainActivity, GameSettings::class.java)
            startActivity(intent)
        }
        // Creates the high scores button which has an onclicklistener to change pages
        val score:Button = findViewById(R.id.HighScoreButton)
        score.setOnClickListener {
            val intent = Intent(this@MainActivity, HighScoresScreen::class.java)
            startActivity(intent)
        }
    }
}
