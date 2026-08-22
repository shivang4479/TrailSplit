package com.example.trailsplit

import android.os.Bundle
import  android.app.DatePickerDialog
import android.widget.EditText
import java.util.Calendar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CreateTripscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_tripscreen)
        val startdate = findViewById<EditText>(R.id.enterstartdate)
        val enddate = findViewById<EditText>(R.id.enterenddate)
        startdate.setOnClickListener {
            showDatePicker(startdate)
        }
        enddate.setOnClickListener {
            showDatePicker(enddate)
        }
    }

        private fun showDatePicker(editText: EditText){
            val calendar= Calendar.getInstance()
            val year=calendar.get(Calendar.YEAR)
            val month=calendar.get(Calendar.MONTH)
            val day=calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog= DatePickerDialog(this,{ _, selectedYear,
             selectedMonth, selectedDay-> editText.setText("$selectedDay/${selectedMonth + 1}/$selectedYear")},
                year,month,day)
            datePickerDialog.show()

        }


        }

