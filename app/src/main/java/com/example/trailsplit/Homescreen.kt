package com.example.trailsplit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class Homescreen : AppCompatActivity() {
    lateinit var  recyclerView:RecyclerView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_homescreen)
        val addexpense=findViewById<Button>(R.id.addExpenseButton)
        addexpense.setOnClickListener {
            val intent = Intent(this, AddExpenseActivity::class.java)
            startActivity(intent)
        }

         recyclerView = findViewById(R.id.expenserecycler)

    }


}