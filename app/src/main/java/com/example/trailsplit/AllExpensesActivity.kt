package com.example.trailsplit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray

class AllExpensesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_all_expenses)

        recyclerView = findViewById(R.id.allExpensesRecycler)

        recyclerView.layoutManager =
            LinearLayoutManager(this)

        adapter = ExpenseAdapter(emptyList())

        recyclerView.adapter = adapter

        loadAllExpenses()
    }

    private fun loadAllExpenses() {

        val sharedPreferences =
            getSharedPreferences("ExpenseData", MODE_PRIVATE)

        val data =
            sharedPreferences.getString("expenses", "[]")

        val jsonArray = JSONArray(data)

        val expenseList = ArrayList<Expense>()

        for (i in 0 until jsonArray.length()) {

            val expenseObject = jsonArray.getJSONObject(i)

            expenseList.add(
                Expense(
                    expenseObject.getString("name"),
                    expenseObject.getString("cost"),
                    expenseObject.getString("date")
                )
            )
        }

        // Newest expenses first
        expenseList.reverse()

        adapter.updateList(expenseList)
    }
}