package com.qi.roomdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyFunctionAdapter(private val functions: List<MyFunction>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_function, parent, false)
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun getItemCount() = functions.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val function = functions[position]
        holder.itemView.findViewById<TextView>(R.id.tv_name).text = function.functionName
        holder.itemView.setOnClickListener {
            function.function.invoke()
        }
    }


    class MyFunction(
        val functionName: String,
        val function: () -> Unit
    )
}
