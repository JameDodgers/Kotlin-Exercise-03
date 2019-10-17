package com.example.pablohenrique.kotlinexercise03

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class TaskAdapter (val context : Context, val tasks : ArrayList<Task>) : BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflater.inflate(android.R.layout.simple_list_item_2, null)
        val description = view.findViewById<TextView>(android.R.id.text1)
        val priority = view.findViewById<TextView>(android.R.id.text2)
        description.text = tasks.get(position).description
        val textPriority = tasks.get(position).priority.toString()
        priority.text = "Priority: $textPriority "
        return view
    }

    override fun getItem(position: Int): Task {
        return tasks[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return tasks.size
    }
}