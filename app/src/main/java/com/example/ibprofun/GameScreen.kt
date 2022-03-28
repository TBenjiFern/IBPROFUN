package com.example.ibprofun

import android.content.Context
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
    var problemCount = 1
    var getPoint = true
    var rand1 = 0
    var rand2 = 0


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
        val sharedPreference =  getApplicationContext().getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        var qAmount = sharedPreference.getString("qAmount"," ")
        var multiplier = sharedPreference.getString("multiplier"," ")
        var dificulty = sharedPreference.getString("difficulty", "")
        setContentView(R.layout.game_screen)
        val title = findViewById<TextView>(R.id.OperatorTitle)

        var progress = findViewById<TextView>(R.id.progress_view)

        progress.text = problemCount.toString() + "/" + qAmount

        title.text = "Addition"
        if (qAmount != null) {
            if (problemCount != qAmount.toInt() + 1) {
                problemCount += 1
            }
            else {
                val i = Intent(this@GameScreen, GameSelection::class.java)
                startActivity(i)
                var displayScore = scoreInt.toFloat();
                if (getPoint) {
                    displayScore += 1;
                }

                if (multiplier != null) {
                    displayScore *= multiplier.toFloat()
                }

                val text = "Your score was " + displayScore.toString()
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }
        }
        val score = findViewById<TextView>(R.id.Counter)
        var newScoreInt: Int = scoreInt
        if (getPoint) {
            newScoreInt += 1
        }
        score.setText(newScoreInt.toString())
        val button: Button = findViewById(R.id.backButton)

        button.setOnClickListener {
            val i = Intent(this@GameScreen, GameSelection::class.java)
            startActivity(i)
        }
        val check_button = findViewById<Button>(R.id.Answer_button)
        if (dificulty == "Easy") {
            rand1 = (1..10).random()
            rand2 = (1..10).random()
        }
        if (dificulty == "Medium") {
            rand1 = (1..100).random()
            rand2 = (1..100).random()
        }
        if (dificulty == "Hard") {
            rand1 = (1..500).random()
            rand2 = (1..500).random()
        }

        val question = findViewById<TextView>(R.id.Equation)
        question.text = "$rand1 + $rand2"

        check_button.setOnClickListener { check_user_answer(rand1, rand2, "add", newScoreInt) }
    }

    private fun subtraction(scoreInt: Int) {
        val sharedPreference =  getApplicationContext().getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        var qAmount = sharedPreference.getString("qAmount"," ")
        var multiplier = sharedPreference.getString("multiplier"," ")
        var dificulty = sharedPreference.getString("difficulty", "")
        setContentView(R.layout.game_screen)
        val title = findViewById<TextView>(R.id.OperatorTitle)
        title.text = "Subtraction"

        var progress = findViewById<TextView>(R.id.progress_view)

        progress.text = problemCount.toString() + "/" + qAmount


        if (qAmount != null) {
            if (problemCount != qAmount.toInt() + 1) {
                problemCount += 1
            }
            else {
                val i = Intent(this@GameScreen, GameSelection::class.java)
                startActivity(i)
                var displayScore = scoreInt.toFloat();
                if (getPoint) {
                    displayScore += 1;
                }

                if (multiplier != null) {
                    displayScore *= multiplier.toFloat()
                }

                val text = "Your score was " + displayScore.toString()
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }
        }
        val score = findViewById<TextView>(R.id.Counter)
        var newScoreInt: Int = scoreInt
        if (getPoint) {
            newScoreInt += 1
        }
        score.setText(newScoreInt.toString())
        val button: Button = findViewById(R.id.backButton)
        
        button.setOnClickListener {
            val i = Intent(this@GameScreen, GameSelection::class.java)
            startActivity(i)
        }
        val check_button = findViewById<Button>(R.id.Answer_button)
        if (dificulty == "Easy") {
            rand1 = (1..10).random()
            rand2 = (1..10).random()
        }
        if (dificulty == "Medium") {
            rand1 = (1..100).random()
            rand2 = (1..100).random()
        }
        if (dificulty == "Hard") {
            rand1 = (1..500).random()
            rand2 = (1..500).random()
        }
        val question = findViewById<TextView>(R.id.Equation)
        var biggerNum = rand2
        var smallerNum = rand1
        if (rand1 >= rand2){
            biggerNum = rand1
            smallerNum = rand2
        }
        question.text = "$biggerNum - $smallerNum"

        check_button.setOnClickListener { check_user_answer(biggerNum, smallerNum, "sub",newScoreInt) }
    }

    private fun multiplication(scoreInt: Int) {
        val sharedPreference =  getApplicationContext().getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        var qAmount = sharedPreference.getString("qAmount"," ")
        var multiplier = sharedPreference.getString("multiplier"," ")
        var dificulty = sharedPreference.getString("difficulty", "")
        setContentView(R.layout.game_screen)
        val title = findViewById<TextView>(R.id.OperatorTitle)
        title.text = "Multiplication"

        var progress = findViewById<TextView>(R.id.progress_view)

        progress.text = problemCount.toString() + "/" + qAmount


        if (qAmount != null) {
            if (problemCount != qAmount.toInt() + 1) {
                problemCount += 1
            }
            else {
                val i = Intent(this@GameScreen, GameSelection::class.java)
                startActivity(i)
                var displayScore = scoreInt.toFloat();
                if (getPoint) {
                    displayScore += 1;
                }

                if (multiplier != null) {
                    displayScore *= multiplier.toFloat()
                }

                val text = "Your score was " + displayScore.toString()
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }
        }
        val score = findViewById<TextView>(R.id.Counter)
        var newScoreInt: Int = scoreInt
        if (getPoint) {
            newScoreInt += 1
        }
        score.setText(newScoreInt.toString())
        val button: Button = findViewById(R.id.backButton)

        button.setOnClickListener {
            val i = Intent(this@GameScreen, GameSelection::class.java)
            startActivity(i)
        }
        val check_button = findViewById<Button>(R.id.Answer_button)
        if (dificulty == "Easy") {
            rand1 = (1..10).random()
            rand2 = (1..10).random()
        }
        if (dificulty == "Medium") {
            rand1 = (5..10).random()
            rand2 = (5..25).random()
        }
        if (dificulty == "Hard") {
            rand1 = (5..25).random()
            rand2 = (10..25).random()
        }
        val question = findViewById<TextView>(R.id.Equation)
        question.text = "$rand1 X $rand2"

        check_button.setOnClickListener { check_user_answer(rand1, rand2, "mult",newScoreInt) }
    }

    private fun division(scoreInt: Int) {
        val sharedPreference =  getApplicationContext().getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        var qAmount = sharedPreference.getString("qAmount"," ")
        var multiplier = sharedPreference.getString("multiplier"," ")
        var dificulty = sharedPreference.getString("difficulty", "")
        setContentView(R.layout.game_screen)
        val title = findViewById<TextView>(R.id.OperatorTitle)
        title.text = "Division"

        var progress = findViewById<TextView>(R.id.progress_view)

        progress.text = problemCount.toString() + "/" + qAmount


        if (qAmount != null) {
            if (problemCount != qAmount.toInt() + 1) {
                problemCount += 1
            }
            else {
                val i = Intent(this@GameScreen, GameSelection::class.java)
                startActivity(i)
                var displayScore = scoreInt.toFloat();
                if (getPoint) {
                    displayScore += 1;
                }

                if (multiplier != null) {
                    displayScore *= multiplier.toFloat()
                }

                val text = "Your score was " + displayScore.toString()
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }
        }
        val score = findViewById<TextView>(R.id.Counter)
        var newScoreInt: Int = scoreInt
        if (getPoint) {
            newScoreInt += 1
        }
        score.setText(newScoreInt.toString())
        val button: Button = findViewById(R.id.backButton)

        button.setOnClickListener {
            val i = Intent(this@GameScreen, GameSelection::class.java)
            startActivity(i)
        }
        val check_button = findViewById<Button>(R.id.Answer_button)
        if (dificulty == "Easy") {
            rand1 = (1..10).random()
            rand2 = (1..10).random()
        }
        if (dificulty == "Medium") {
            rand1 = (5..10).random()
            rand2 = (5..25).random()
        }
        if (dificulty == "Hard") {
            rand1 = (5..25).random()
            rand2 = (10..25).random()
        }
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
                getPoint = true


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

                getPoint = false
                background.setBackgroundColor(Color.RED);
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
        } catch (e : Exception) {
            Toast.makeText(this, "Invalid Input!", Toast.LENGTH_LONG).show()
    }
}}