package com.example.ibprofun

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
        if (mode == "addition") {
            addition(-1)
        }
        if (mode == "subtraction") {
            subtraction(-1)
        }
        if (mode == "multiplication") {
            multiplication(-1)
        }
        if (mode == "division") {
            division(-1)
        }

    }



    private fun addition(scoreInt: Int) {
        setContentView(R.layout.game_screen)
        val title = findViewById<TextView>(R.id.OperatorTitle)
        title.text = "Addition"

        val score = findViewById<TextView>(R.id.Counter)
        val newScoreInt: Int = scoreInt + 1
        score.setText(newScoreInt.toString())
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

        check_button.setOnClickListener { check_user_answer(rand1, rand2, "add", newScoreInt) }
    }

    private fun subtraction(scoreInt: Int) {
        setContentView(R.layout.game_screen)
        val title = findViewById<TextView>(R.id.OperatorTitle)
        title.text = "Subtraction"

        val score = findViewById<TextView>(R.id.Counter)
        val newScoreInt: Int = scoreInt + 1
        score.setText(newScoreInt.toString())
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

        check_button.setOnClickListener { check_user_answer(rand1, rand2, "sub",newScoreInt) }
    }

    private fun multiplication(scoreInt: Int) {
        setContentView(R.layout.game_screen)
        val title = findViewById<TextView>(R.id.OperatorTitle)
        title.text = "Multiplication"

        val score = findViewById<TextView>(R.id.Counter)
        val newScoreInt: Int = scoreInt + 1
        score.setText(newScoreInt.toString())
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

        check_button.setOnClickListener { check_user_answer(rand1, rand2, "mult",newScoreInt) }
    }

    private fun division(scoreInt: Int) {
        setContentView(R.layout.game_screen)
        val title = findViewById<TextView>(R.id.OperatorTitle)
        title.text = "Division"

        val score = findViewById<TextView>(R.id.Counter)
        val newScoreInt: Int = scoreInt + 1
        score.setText(newScoreInt.toString())
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

        check_button.setOnClickListener { check_user_answer(product, rand2, "div",newScoreInt) }
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


    private fun check_user_answer(rand1: Int,rand2: Int,mode: String, scoreInt: Int) {
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
                        addition(scoreInt)
                    }
                    if (mode == "sub") {
                        subtraction(scoreInt)
                    }
                    if (mode == "mult") {
                        multiplication(scoreInt)
                    }
                    if (mode == "div") {
                        division(scoreInt)
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