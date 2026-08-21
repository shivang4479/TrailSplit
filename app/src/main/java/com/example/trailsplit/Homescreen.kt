package com.example.trailsplit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class Homescreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_homescreen)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        bottomNav.setOnItemSelectedListener { item ->

            when (item.itemId) {

                R.id.home_icon -> {
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.trip_icon -> {
                    Toast.makeText(this, "Trip", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.add_icon -> {
                    Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.sync_icon -> {
                    Toast.makeText(this, "Sync", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.Profile_icon -> {
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }

        bottomNav.selectedItemId = R.id.home_icon
    }
}