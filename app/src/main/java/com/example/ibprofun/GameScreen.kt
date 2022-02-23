package com.example.ibprofun

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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
        val check_button = findViewById<Button>(R.id.check_answer)
        val rand1 = (0..10).random()
        val rand2 = (0..10).random()
        val question = findViewById<TextView>(R.id.auto_question)
        question.text = "$rand1 + $rand2"

        check_button.setOnClickListener { check_user_answer(rand1, rand2) }
    }

    private fun again() {
        setContentView(R.layout.game_screen)
        val button: Button = findViewById(R.id.return_game_select)
        button.setOnClickListener {
            val i = Intent(this@GameScreen, GameSelection::class.java)
            startActivity(i)
        }
        val check_button = findViewById<Button>(R.id.check_answer)
        val rand1 = (0..10).random()
        val rand2 = (0..10).random()
        val question = findViewById<TextView>(R.id.auto_question)
        question.text = "$rand1 + $rand2"

        check_button.setOnClickListener { check_user_answer(rand1, rand2) }
    }

    private fun check_user_answer(rand1: Int,rand2: Int) {
        try {

            val user_answer = findViewById<EditText>(R.id.user_answer)
            val user_input = user_answer.text.toString().toInt()
            val answer_output = (rand1 + rand2)
            if (answer_output == user_input) {
                val answer_solution = findViewById<TextView>(R.id.answer_solution)
                again()
            }
            else{
                val answer_solution = findViewById<TextView>(R.id.answer_solution)
                answer_solution.text ="Wrong"
                answer_solution.postDelayed(Runnable { answer_solution.setVisibility(View.GONE) }, 5000)
            }
        } catch (e : Exception) {
            Toast.makeText(this, "Invalid Input!", Toast.LENGTH_LONG).show()
    }
}}