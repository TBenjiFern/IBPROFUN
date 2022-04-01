package com.example.ibprofun

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class GameSelection : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This sets the view to the game select screen
        setContentView(R.layout.game_selection)
        // Find the buttons on the screen and save them into variables for use in a second
        val addButton: Button = findViewById(R.id.additionButton)
        val subButton: Button = findViewById(R.id.subtractionButton)
        val multButton: Button = findViewById(R.id.multiplicationButton)
        val divButton: Button = findViewById(R.id.divisionButton)
        // Creates back button to return to the intro screen
        val button: Button = findViewById(R.id.return_to_main_select)
        button.setOnClickListener {
            val i = Intent(this@GameSelection, MainActivity::class.java)
            startActivity(i)
        }
        // If the add button is pressed, this onclicklistener will pass a variable to the next page
        // (the game screen) which will let it know we are playing in addition mode
        addButton.setOnClickListener {
            val i = Intent(this@GameSelection, GameScreen::class.java)
            // This .putExtra passes a variable to the next intent/page
            i.putExtra("mode","addition")
            startActivity(i)
        }
        // If the subtract button is pressed, this will set the game into subtraction mode
        subButton.setOnClickListener {
            val i = Intent(this@GameSelection, GameScreen::class.java)
            i.putExtra("mode","subtraction")
            startActivity(i)
        }
        // If the multiply button is pressed, this will set the game into multiplication mode
        multButton.setOnClickListener {
            val i = Intent(this@GameSelection, GameScreen::class.java)
            i.putExtra("mode","multiplication")
            startActivity(i)
        }
        // If the divide button is pressed, this will set the game into division mode
        divButton.setOnClickListener {
            val i = Intent(this@GameSelection, GameScreen::class.java)
            i.putExtra("mode","division")
            startActivity(i)
        }
    }
}