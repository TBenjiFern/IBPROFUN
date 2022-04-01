package com.example.ibprofun

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

//NOTE if the highscores stop displaying it's because access from Firebase has expired.
// You'd need to go into the firebase rules and extend the expiration date in order to use this database again
// Currently set to expire on April 10th, 2022


class HighScoresScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set view to high score screen
        setContentView(R.layout.high_scores_screen)
        // This button allows the user to return to the intro screen
        val button: Button = findViewById(R.id.highScoreBack)
        button.setOnClickListener {
            // When the listener is triggered, we create a new intent to move to MainActivity then start it
            val i = Intent(this@HighScoresScreen, MainActivity::class.java)
            startActivity(i)
        }
        // Create a variable/object linking to the firebase
        val db = FirebaseFirestore.getInstance()

        // Access the "highscores" collection within the database
        db.collection("highscores")
            // withdraw the information from this collection using get()
            .get()
            // Error handling for success or failure
            .addOnSuccessListener { result ->
                // Get the text view from the page
                val textArea = findViewById<TextView>(R.id.mainArea)
//                textArea.setText(result.toString())
                var displayText = ""
                // We need to save the data from the collection into an array so that we can sort it in a bit
                var highList = arrayListOf<Float>()
                for (document in result) {
                    // For each item, add it into this list by converting it to string then to float
                    var value = document.data.values.toList()[0].toString().toFloat()
                    highList.add(value)
                }
                // Now sort so that we can get the top 5 scores
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
                    // Format the text so that it's numbered and in order
                    displayText += counter.toString() + " ........ " + value + "\n"
                }
                // Set the text on screen
                textArea.setText(displayText)
            }
            // If this operation fails, send error exception
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }
}