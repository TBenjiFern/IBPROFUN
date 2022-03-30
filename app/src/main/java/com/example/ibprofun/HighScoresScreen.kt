package com.example.ibprofun

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class HighScoresScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.high_scores_screen)

        val button: Button = findViewById(R.id.highScoreBack)
        button.setOnClickListener {
            // When the listener is triggered, we create a new intent to move to MainActivity then start it
            val i = Intent(this@HighScoresScreen, MainActivity::class.java)
            startActivity(i)
        }

        val db = FirebaseFirestore.getInstance()

        db.collection("highscores")
            .get()
            .addOnSuccessListener { result ->
                val textArea = findViewById<TextView>(R.id.mainArea)
//                textArea.setText(result.toString())
                var displayText = ""

                var highList = arrayListOf<Float>()
                for (document in result) {
                    var value = document.data.values.toList()[0].toString().toFloat()
                    highList.add(value)
                }
                highList.sort()
                var counter = 0
                for (i in 0..4) {
                    var value = ""
                    counter += 1
                    if (i >= highList.size){
                        value = ""
                    }else{
                        value = highList[highList.size-1-i].toString()
                    }
                    displayText += counter.toString() + " ........ " + value + "\n"
                }

                textArea.setText(displayText)
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
//        val start:Button = findViewById(R.id.startLearningButton)
//        start.setOnClickListener {
//            val intent = Intent(this@MainActivity, GameSelection::class.java)
//            startActivity(intent)
//        }
//        val setting:Button = findViewById(R.id.gameSettingsButton)
//        setting.setOnClickListener {
//            val intent = Intent(this@MainActivity, GameSettings::class.java)
//            startActivity(intent)
//        }
    }
}