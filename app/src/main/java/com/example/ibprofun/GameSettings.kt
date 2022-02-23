package com.example.ibprofun

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class GameSettings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_setting_selection)
        val button: Button = findViewById(R.id.return_to_main)
        button.setOnClickListener {
            val i = Intent(this@GameSettings, MainActivity::class.java)
            startActivity(i)
        }
    }
}