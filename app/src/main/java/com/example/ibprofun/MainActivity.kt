package com.example.ibprofun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//      Links the layout to the kotlin file.
        setContentView(R.layout.intro_screen)
    }
}