package com.example.trailsplit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val signbtn = findViewById<TextView>(R.id.sign)
        signbtn.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }

        val forgotbtn=findViewById<TextView>(R.id.fogetpassword)
        forgotbtn.setOnClickListener {
            val intent= Intent(this, Forgetpassword::class.java)
            startActivity(intent)
        }

        val loginbtn=findViewById<Button>(R.id.btn)
        loginbtn.setOnClickListener {
            val intent=Intent(this, Homescreen::class.java)
            startActivity(intent)

        }

    }

    }
