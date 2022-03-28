package com.example.ibprofun

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class HighScoresScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.high_scores_screen)

        val db = FirebaseFirestore.getInstance()

        val docRef = db.collection("highscores").document("SF")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
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