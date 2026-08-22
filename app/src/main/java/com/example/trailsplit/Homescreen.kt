package com.example.trailsplit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Homescreen : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var expenseAdapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_homescreen)

        // Add Expense button
        val addExpense = findViewById<Button>(R.id.addExpenseButton)

        addExpense.setOnClickListener {
            val intent = Intent(this, AddExpenseActivity::class.java)
            startActivity(intent)
        }

        // View All button
        val viewAll = findViewById<TextView>(R.id.viewAll)

        viewAll.setOnClickListener {
            val intent = Intent(this, AllExpensesActivity::class.java)
            startActivity(intent)
        }

        // RecyclerView
        recyclerView = findViewById(R.id.expenserecycler)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Create adapter with empty list initially
        expenseAdapter = ExpenseAdapter(emptyList())

        recyclerView.adapter = expenseAdapter

        // Load saved expenses
        loadExpenses()
    }

    override fun onResume() {
        super.onResume()

        // Reload expenses whenever we return to HomeScreen
        if (::expenseAdapter.isInitialized) {
            loadExpenses()
        }
    }

    private fun loadExpenses() {

        val sharedPreferences = getSharedPreferences(
            "ExpenseData",
            MODE_PRIVATE
        )

        val json = sharedPreferences.getString(
            "expenses",
            null
        )

        if (json == null) {
            expenseAdapter.updateList(emptyList())
            return
        }

        val type = object : TypeToken<List<Expense>>() {}.type

        val allExpenses: List<Expense> =
            Gson().fromJson(json, type)

        // Get the 10 most recent expenses
        val homeExpenses = allExpenses
            .takeLast(10)
            .reversed()

        expenseAdapter.updateList(homeExpenses)
    }
}