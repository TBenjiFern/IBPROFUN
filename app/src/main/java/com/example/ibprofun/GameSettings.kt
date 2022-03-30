package com.example.ibprofun

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Half.toFloat
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore


class GameSettings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This will enable the program to set the view to game_settings_selection upon being called
        setContentView(R.layout.game_setting_selection)
        // This button gives functionality to the back button on the page
        val button: Button = findViewById(R.id.return_to_main)
        button.setOnClickListener {
            // When the listener is triggered, we create a new intent to move to MainActivity then start it
            val i = Intent(this@GameSettings, MainActivity::class.java)
            startActivity(i)
        }
        // Create all variables for buttons and textviews on the screen here to keep it in scope of all the functions
        val qAmountGroup = findViewById<RadioGroup>(R.id.questionsGroup)
        val difficultyGroup = findViewById<RadioGroup>(R.id.difficultiesGroup)
        val qAmountButton10 = findViewById<RadioButton>(R.id.ques_10)
        val qAmountButton20 = findViewById<RadioButton>(R.id.ques_20)
        val qAmountButton30 = findViewById<RadioButton>(R.id.ques_30)
        val diffButtonEasy = findViewById<RadioButton>(R.id.diff_easy)
        val diffButtonMedium = findViewById<RadioButton>(R.id.diff_medium)
        val diffButtonHard = findViewById<RadioButton>(R.id.diff_hard)
        val qAmountMultiplier = findViewById<TextView>(R.id.multiplier1)
        val diffMultiplier2 = findViewById<TextView>(R.id.multiplier2)
        val multiplierTotal = findViewById<TextView>(R.id.multiplierTotal)
        val saveSettingsButton = findViewById<Button>(R.id.saveSettingsButton)
        var multiplierNum = "1.0"
        // SharedPreferences lets us access a file to store variables from ths page.
        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        // Create an editor to let us push variables to the sharedpreferences
        var editor = sharedPreference.edit()
        // Create an initial push so that we have some default settings which match the button positions on the page
        editor.putString("qAmount", "10")
        editor.putString("difficulty", "easy")
        editor.putString("multiplier", multiplierNum)
        editor.commit()

        fun calcTotal(): String {
            // This function will make a sum total from all the multipliers on the page
            multiplierNum = (qAmountMultiplier.getText().toString().toFloat() * diffMultiplier2.getText().toString().toFloat()).toString()
            // Return sum value
            return multiplierNum
        }

        fun setTotalMultiplier(totalAmount: String) {
            // This function updates the total multiplier text on the page
            multiplierTotal.text = totalAmount
        }

        // List of listeners to update the multiplier text when a button is pressed
        qAmountButton10.setOnClickListener {
            // Change text
            qAmountMultiplier.text = "1.0"
            // Get new total multiplier value
            multiplierNum = calcTotal()
            // Update total multiplier score with total
            setTotalMultiplier(multiplierNum)
        }

        qAmountButton20.setOnClickListener {
            qAmountMultiplier.text = "1.5"
            multiplierNum = calcTotal()
            setTotalMultiplier(multiplierNum)
        }

        qAmountButton30.setOnClickListener {
            qAmountMultiplier.text = "2.0"
            multiplierNum = calcTotal()
            setTotalMultiplier(multiplierNum)
        }

        diffButtonEasy.setOnClickListener {
            diffMultiplier2.text = "1.0"
            multiplierNum = calcTotal()
            setTotalMultiplier(multiplierNum)
        }

        diffButtonMedium.setOnClickListener {
            diffMultiplier2.text = "2.0"
            multiplierNum = calcTotal()
            setTotalMultiplier(multiplierNum)
        }

        diffButtonHard.setOnClickListener {
            diffMultiplier2.text = "3.0"
            multiplierNum = calcTotal()
            setTotalMultiplier(multiplierNum)
        }

        // When save settings button is hit, perform operations
        saveSettingsButton.setOnClickListener{
            // Read string value from both button groups and save it
            val qAmountId = qAmountGroup.checkedRadioButtonId
            val qAmountRadioButton = findViewById<RadioButton>(qAmountId)
            val qAmountText = qAmountRadioButton.getText().toString()

            val difficultiesId = difficultyGroup.checkedRadioButtonId
            val difficultyRadioButton = findViewById<RadioButton>(difficultiesId)
            val difficultyText = difficultyRadioButton.getText().toString()

            // Save the string values from the buttons to shared preferences
            editor.putString("qAmount", qAmountText)
            editor.putString("difficulty", difficultyText)
            editor.putString("multiplier", multiplierNum)
            editor.commit()

            // Inform user that changes are saved using Toast
            Toast.makeText(this@GameSettings, "Settings Saved!", Toast.LENGTH_SHORT).show()
        }

    }




//
//    //      Initialize the firebase
//    val db = FirebaseFirestore.getInstance()
//
//    // Create a new user with a first and last name
//    val user: MutableMap<String, Any> = HashMap()
//    user["first"] = "Ada"
//    user["last"] = "Lovelace"
//    user["born"] = 1815
//
//
//
//    // Add a new document with a generated ID
//    db.collection("users")
//    .add(user)
//    .addOnSuccessListener(OnSuccessListener<DocumentReference> { documentReference ->
//        Log.d(
//            TAG,
//            "DocumentSnapshot added with ID: " + documentReference.id
//        )
//    })
//    .addOnFailureListener(OnFailureListener { e -> Log.w(TAG, "Error adding document", e) })
//    Java Code to get from the database

//    db.collection("users")
//    .get()
//    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//        @Override
//        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//            if (task.isSuccessful()) {
//                for (QueryDocumentSnapshot document : task.getResult()) {
//                    Log.d(TAG, document.getId() + " => " + document.getData());
//                }
//            } else {
//                Log.w(TAG, "Error getting documents.", task.getException());
//            }
//        }
//    });









}

//Additional Notes
//Securing Data
//If you're using the Web, Android, or iOS SDK, use Firebase Authentication and Cloud Firestore Security Rules to secure your data in Cloud Firestore.
//If you're using one of the server SDKs, use Identity and Access Management (IAM) to secure your data in Cloud Firestore.

//Next Steps
//Data model — Learn more about how data is structured in Cloud Firestore, including hierarchical data and subcollections.
//https://firebase.google.com/docs/firestore/data-model?utm_source=studio
//Add data — Learn more about creating and updating data in Cloud Firestore.
//https://firebase.google.com/docs/firestore/manage-data/add-data?utm_source=studio
//Get data — Learn more about how to retrieve data.
//https://firebase.google.com/docs/firestore/query-data/get-data?utm_source=studio
//Perform simple and compound queries — Learn how to run simple and compound queries.
//https://firebase.google.com/docs/firestore/query-data/queries?utm_source=studio
//Order and limit queries — Learn how to order and limit the data returned by your queries.
//https://firebase.google.com/docs/firestore/query-data/order-limit-data?utm_source=studio