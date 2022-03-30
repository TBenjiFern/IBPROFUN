package com.example.ibprofun

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class GameScreen: AppCompatActivity() {
//    variables that are able to be accessed/ changed by all functions within the class
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
//        grab the mode selected by the user and run correct function
//        1 is subtracted to make sure that the score is correct
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
//        the next four lines used sharedPreferences which are variables that are global across the whole application
        val sharedPreference =  getApplicationContext().getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        var qAmount = sharedPreference.getString("qAmount"," ")
        var multiplier = sharedPreference.getString("multiplier"," ")
        var dificulty = sharedPreference.getString("difficulty", "")
        setContentView(R.layout.game_screen)
        val title = findViewById<TextView>(R.id.OperatorTitle)

        var progress = findViewById<TextView>(R.id.progress_view)
//      Display how many questions have been completed
        progress.text = problemCount.toString() + "/" + qAmount

        title.text = "Addition"

        if (qAmount != null) {
//            Check if the user still has more questions to complete
            if (problemCount != qAmount.toInt() + 1) {
                problemCount += 1
            }
//            If there are no more questions for the user to do, this will return the user to the game select screen while using a toast to display the score received
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

                val db = FirebaseFirestore.getInstance()

                // Create a new user with a first and last name
                val highscore: MutableMap<String, Any> = HashMap()
                val date = Calendar.getInstance().time
                val formatter = SimpleDateFormat.getDateTimeInstance() //or use getDateInstance()
                val formatedDate = formatter.format(date)

                highscore[formatedDate] = displayScore.toString()


                // Add a new document with a generated ID
                db.collection("highscores")
                    .add(highscore)
                    .addOnSuccessListener(OnSuccessListener<DocumentReference> { documentReference ->
                        Log.d(
                            ContentValues.TAG,
                            "DocumentSnapshot added with ID: " + documentReference.id
                        )
                    })
                    .addOnFailureListener(OnFailureListener { e ->
                        Log.w(
                            ContentValues.TAG,
                            "Error adding document",
                            e
                        )
                    })




            }
        }
        val score = findViewById<TextView>(R.id.Counter)
        var newScoreInt: Int = scoreInt
//        If the user got the question correct, this will increase there score by one
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
//        these are the three difficulties and will run based on what the user selected in settings
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
//        displays question
        question.text = "$rand1 + $rand2"
//      When the check button is selected it will display if the user is right or wrong then move on to the next question
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

                val db = FirebaseFirestore.getInstance()

                // Create a new user with a first and last name
                val highscore: MutableMap<String, Any> = HashMap()
                highscore["1"] = displayScore.toString()


                db.collection("highscores")
                    .add(highscore)
                    .addOnSuccessListener(OnSuccessListener<DocumentReference> { documentReference ->
                        Log.d(
                            ContentValues.TAG,
                            "DocumentSnapshot added with ID: " + documentReference.id
                        )
                    })
                    .addOnFailureListener(OnFailureListener { e ->
                        Log.w(
                            ContentValues.TAG,
                            "Error adding document",
                            e
                        )
                    })
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

//        Check which number is larger and makes sure that it is first before displaying
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

                val db = FirebaseFirestore.getInstance()

                // Create a new user with a first and last name
                val highscore: MutableMap<String, Any> = HashMap()
                highscore["1"] = displayScore.toString()


                db.collection("highscores")
                    .add(highscore)
                    .addOnSuccessListener(OnSuccessListener<DocumentReference> { documentReference ->
                        Log.d(
                            ContentValues.TAG,
                            "DocumentSnapshot added with ID: " + documentReference.id
                        )
                    })
                    .addOnFailureListener(OnFailureListener { e ->
                        Log.w(
                            ContentValues.TAG,
                            "Error adding document",
                            e
                        )
                    })
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

                val db = FirebaseFirestore.getInstance()

                // Create a new user with a first and last name
                val highscore: MutableMap<String, Any> = HashMap()
                highscore["1"] = displayScore.toString()


                db.collection("highscores")
                    .add(highscore)
                    .addOnSuccessListener(OnSuccessListener<DocumentReference> { documentReference ->
                        Log.d(
                            ContentValues.TAG,
                            "DocumentSnapshot added with ID: " + documentReference.id
                        )
                    })
                    .addOnFailureListener(OnFailureListener { e ->
                        Log.w(
                            ContentValues.TAG,
                            "Error adding document",
                            e
                        )
                    })
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
//        To find a problem two number are multiplied and the solution becomes one of the question numbers
        val product = rand1 * rand2
        val question = findViewById<TextView>(R.id.Equation)
        question.text = "$product / $rand2"

        check_button.setOnClickListener { check_user_answer(product, rand2, "div",newScoreInt) }
    }

//    receives the solution of each question for all of the game options
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


//    Check if the user is correct or not
    private fun check_user_answer(rand1: Int,rand2: Int,mode: String, scoreInt: Int) {
        try {
            val user_answer = findViewById<EditText>(R.id.Answer_input)
            val background = findViewById<ConstraintLayout>(R.id.background)
            val user_input = user_answer.text.toString().toInt()

//          If the user gets the question correct it will display a message and change the color of the screen then move to the next question
            if (getSolution(rand1,rand2,mode) == user_input) {
                val answer_solution = findViewById<TextView>(R.id.Check_answer)
                answer_solution.text = "Correct"
                getPoint = true


                background.setBackgroundColor(Color.GREEN);
                Handler(Looper.getMainLooper()).postDelayed({
//                     call the function again until the questions are done
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
//                If the user is wrong change the color to red, display the correct answer then move on to the next question until the user has finished
                val answer_solution = findViewById<TextView>(R.id.Check_answer)
                val answer = (getSolution(rand1,rand2,mode).toString())
                answer_solution.text ="$answer"

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