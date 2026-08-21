package com.example.trailsplit

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
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

        expenseNameInput = findViewById(R.id.expenseNameInput)
        expenseCostInput = findViewById(R.id.expenseCostInput)
        expenseDateInput = findViewById(R.id.expenseDateInput)
        saveExpenseButton = findViewById(R.id.saveExpenseButton)

        // Open date picker
        expenseDateInput.setOnClickListener {
            showDatePicker()
        }

        // Add Expense button
        saveExpenseButton.setOnClickListener {

            val name = expenseNameInput.text.toString().trim()
            val cost = expenseCostInput.text.toString().trim()
            val date = expenseDateInput.text.toString().trim()

            if (name.isEmpty()) {
                expenseNameInput.error = "Enter expense name"
                return@setOnClickListener
            }

            if (cost.isEmpty()) {
                expenseCostInput.error = "Enter expense cost"
                return@setOnClickListener
            }

            if (date.isEmpty()) {
                expenseDateInput.error = "Select expense date"
                return@setOnClickListener
            }

            saveExpense(name, cost, date)

            Toast.makeText(
                this,
                "Expense added successfully",
                Toast.LENGTH_SHORT
            ).show()

            val intent = Intent(this, Homescreen::class.java)

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

        val sharedPreferences =
            getSharedPreferences("ExpenseData", MODE_PRIVATE)

        val oldData =
            sharedPreferences.getString("expenses", "[]")

        val jsonArray = JSONArray(oldData)

        val expenseObject = JSONObject()

        expenseObject.put("name", name)
        expenseObject.put("cost", cost)
        expenseObject.put("date", date)

        jsonArray.put(expenseObject)

        sharedPreferences.edit()
            .putString("expenses", jsonArray.toString())
            .apply()
    }
}