package com.example.trailsplit

import android.content.Intent
import android.graphics.Insets.add
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
        val bottomnav=findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomnav.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.home_icon->{
                    true
                }
                R.id.trip_icon->{
                    true
                }
                R.id.add_icon->{
                    startActivity(Intent(this, CreateTripscreen::class.java))
                    true
                }
                R.id.sync_icon->{
                    true
                }
                R.id.Profile_icon->{
                    true
                }
                else -> false
            }
        }


         recyclerView = findViewById(R.id.expenserecycler)

    }


}