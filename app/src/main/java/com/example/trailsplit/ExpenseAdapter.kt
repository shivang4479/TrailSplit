package com.example.trailsplit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpenseAdapter(
    private var expenseList: List<Expense>
) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    class ExpenseViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val expenseName: TextView =
            itemView.findViewById(R.id.expenseName)

        val expenseCost: TextView =
            itemView.findViewById(R.id.expenseCost)

        val expenseDate: TextView =
            itemView.findViewById(R.id.expenseDate)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpenseViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_expense, parent, false)

        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ExpenseViewHolder,
        position: Int
    ) {

        val expense = expenseList[position]

        holder.expenseName.text = expense.expenseName
        holder.expenseCost.text = "₹${expense.expenseCost}"
        holder.expenseDate.text = expense.expenseDate
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    fun updateList(newList: List<Expense>) {
        expenseList = newList
        notifyDataSetChanged()
    }
}