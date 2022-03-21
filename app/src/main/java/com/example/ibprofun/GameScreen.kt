package com.example.ibprofun

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class GameScreen: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_screen)
        val mode = intent.getStringExtra("mode")
        val button: Button = findViewById(R.id.backButton)
        button.setOnClickListener {
            val i = Intent(this@GameScreen, GameSelection::class.java)
            startActivity(i)
        }
        if (mode == "add") {
            addition()
        }
        if (mode == "sub") {
            subtraction()
        }
        if (mode == "mult") {
            multiplication()
        }
        if (mode == "div") {
            division()
        }

    }



    private fun addition() {
        setContentView(R.layout.game_screen)
        val button: Button = findViewById(R.id.backButton)
        button.setOnClickListener {
            val i = Intent(this@GameScreen, GameSelection::class.java)
            startActivity(i)
        }
        val check_button = findViewById<Button>(R.id.Answer_button)
        val rand1 = (1..10).random()
        val rand2 = (1..10).random()
        val question = findViewById<TextView>(R.id.Equation)
        question.text = "$rand1 + $rand2"

        check_button.setOnClickListener { check_user_answer(rand1, rand2, "add") }
    }

    private fun subtraction() {
        setContentView(R.layout.game_screen)
        val button: Button = findViewById(R.id.backButton)
        button.setOnClickListener {
            val i = Intent(this@GameScreen, GameSelection::class.java)
            startActivity(i)
        }
        val check_button = findViewById<Button>(R.id.Answer_button)
        val rand1 = (5..10).random()
        val rand2 = (1..4).random()
        val question = findViewById<TextView>(R.id.Equation)
        question.text = "$rand1 - $rand2"

        check_button.setOnClickListener { check_user_answer(rand1, rand2, "sub") }
    }

    private fun multiplication() {
        setContentView(R.layout.game_screen)
        val button: Button = findViewById(R.id.backButton)
        button.setOnClickListener {
            val i = Intent(this@GameScreen, GameSelection::class.java)
            startActivity(i)
        }
        val check_button = findViewById<Button>(R.id.Answer_button)
        val rand1 = (1..10).random()
        val rand2 = (1..10).random()
        val question = findViewById<TextView>(R.id.Equation)
        question.text = "$rand1 X $rand2"

        check_button.setOnClickListener { check_user_answer(rand1, rand2, "mult") }
    }

    private fun division() {
        setContentView(R.layout.game_screen)
        val button: Button = findViewById(R.id.backButton)
        button.setOnClickListener {
            val i = Intent(this@GameScreen, GameSelection::class.java)
            startActivity(i)
        }
        val check_button = findViewById<Button>(R.id.Answer_button)
        val rand1 = (1..10).random()
        val rand2 = (1..10).random()
        val product = rand1 * rand2
        val question = findViewById<TextView>(R.id.Equation)
        question.text = "$product / $rand2"

        check_button.setOnClickListener { check_user_answer(product, rand2, "div") }
    }

    private fun getSolution(rand1: Int,rand2: Int,mode: String): Int {
        if (mode == "add"){
            return (rand1 + rand2);
        }
        else if (mode == "sub"){
            return (rand1 - rand2);
        }
        else if (mode == "mult"){
            return (rand1 * rand2);
        }
        else if (mode == "div"){
            return (rand1 / rand2);
        }
        else {
            return 404;
        }
    }


    private fun check_user_answer(rand1: Int,rand2: Int,mode: String) {
        try {
            val user_answer = findViewById<EditText>(R.id.Answer_input)
            val background = findViewById<ConstraintLayout>(R.id.background)
            val user_input = user_answer.text.toString().toInt()


            if (getSolution(rand1,rand2,mode) == user_input) {
                val answer_solution = findViewById<TextView>(R.id.Check_answer)
                answer_solution.text = "Correct"
                background.setBackgroundColor(Color.GREEN);
                Handler(Looper.getMainLooper()).postDelayed({
                    if (mode == "add") {
                        addition()
                    }
                    if (mode == "sub") {
                        subtraction()
                    }
                    if (mode == "mult") {
                        multiplication()
                    }
                    if (mode == "div") {
                        division()
                    }
                }, 1000)

            }
            else{
                val answer_solution = findViewById<TextView>(R.id.Check_answer)
                answer_solution.text ="Wrong"
                background.setBackgroundColor(Color.RED);
                answer_solution.postDelayed(Runnable {
                    answer_solution.text = ""
                    background.setBackgroundColor(Color.WHITE);
                }, 1000)
            }
        } catch (e : Exception) {
            Toast.makeText(this, "Invalid Input!", Toast.LENGTH_LONG).show()
    }
}}