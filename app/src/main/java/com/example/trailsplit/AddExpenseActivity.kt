package com.example.trailsplit

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.util.Calendar

class AddExpenseActivity : AppCompatActivity() {

    private lateinit var expenseNameInput: EditText
    private lateinit var expenseCostInput: EditText
    private lateinit var expenseDateInput: EditText
    private lateinit var saveExpenseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_expense)

        // Connect XML views
        expenseNameInput = findViewById(R.id.expenseNameInput)
        expenseCostInput = findViewById(R.id.expenseCostInput)
        expenseDateInput = findViewById(R.id.expenseDateInput)
        saveExpenseButton = findViewById(R.id.saveExpenseButton)

        // Open date picker
        expenseDateInput.setOnClickListener {
            showDatePicker()
        }



        // Category Section
        val category = findViewById<AutoCompleteTextView>(R.id.dropdowncategory)
        val categoryoptions= arrayOf(
            "Food",
            "Travel",
            "Shopping",
            "Bills",
            "Entertainment",
            "Other"
        )
        val adapter= ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,categoryoptions)
        category.setAdapter(adapter)
        // Save Expense button
        saveExpenseButton.setOnClickListener {

            val name = expenseNameInput.text.toString().trim()
            val cost = expenseCostInput.text.toString().trim()
            val date = expenseDateInput.text.toString().trim()

            // Validate expense name
            if (name.isEmpty()) {
                expenseNameInput.error = "Enter expense name"
                expenseNameInput.requestFocus()
                return@setOnClickListener
            }

            // Validate expense cost
            if (cost.isEmpty()) {
                expenseCostInput.error = "Enter expense cost"
                expenseCostInput.requestFocus()
                return@setOnClickListener
            }

            // Validate date
            if (date.isEmpty()) {
                expenseDateInput.error = "Select expense date"
                expenseDateInput.requestFocus()
                return@setOnClickListener
            }

            // Save expense
            saveExpense(name, cost, date)

            Toast.makeText(
                this,
                "Expense added successfully",
                Toast.LENGTH_SHORT
            ).show()

            // Go back to HomeScreen
            val intent = Intent(
                this,
                Homescreen::class.java
            )

            startActivity(intent)

            finish()
        }
    }

    private fun showDatePicker() {

        val calendar = Calendar.getInstance()

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->

                val date =
                    "$selectedDay/${selectedMonth + 1}/$selectedYear"

                expenseDateInput.setText(date)
            },
            year,
            month,
            day
        )

        datePicker.show()
    }

    private fun saveExpense(
        name: String,
        cost: String,
        date: String
    ) {

        val sharedPreferences = getSharedPreferences(
            "ExpenseData",
            MODE_PRIVATE
        )

        // Get previously saved expenses
        val oldData = sharedPreferences.getString(
            "expenses",
            "[]"
        )

        val jsonArray = try {
            JSONArray(oldData)
        } catch (e: Exception) {
            JSONArray()
        }

        // Create new expense object
        val expenseObject = JSONObject()

        expenseObject.put("name", name)
        expenseObject.put("cost", cost)
        expenseObject.put("date", date)

        // Add new expense to list
        jsonArray.put(expenseObject)

        // Save updated list
        sharedPreferences.edit()
            .putString(
                "expenses",
                jsonArray.toString()
            )
            .apply()
    }
}