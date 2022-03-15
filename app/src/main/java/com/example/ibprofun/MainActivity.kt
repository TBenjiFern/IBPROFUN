package com.example.ibprofun

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro_screen)
        val start:Button = findViewById(R.id.start_button)
        start.setOnClickListener {
            val intent = Intent(this@MainActivity, GameSelection::class.java)
            startActivity(intent)
        }
        val setting:Button = findViewById(R.id.game_setting_button)
        setting.setOnClickListener {
            val intent = Intent(this@MainActivity, GameSettings::class.java)
            startActivity(intent)
        }
    }
}
