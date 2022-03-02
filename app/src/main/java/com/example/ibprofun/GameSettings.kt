package com.example.ibprofun

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore


class GameSettings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_setting_selection)
        val button: Button = findViewById(R.id.return_to_main)
        button.setOnClickListener {
            val i = Intent(this@GameSettings, MainActivity::class.java)
            startActivity(i)
        }



//      Initialize the firebase
        val db = FirebaseFirestore.getInstance()

        // Create a new user with a first and last name
        val user: MutableMap<String, Any> = HashMap()
        user["first"] = "Ada"
        user["last"] = "Lovelace"
        user["born"] = 1815



        // Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener(OnSuccessListener<DocumentReference> { documentReference ->
                Log.d(
                    TAG,
                    "DocumentSnapshot added with ID: " + documentReference.id
                )
            })
            .addOnFailureListener(OnFailureListener { e -> Log.w(TAG, "Error adding document", e) })
    }



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