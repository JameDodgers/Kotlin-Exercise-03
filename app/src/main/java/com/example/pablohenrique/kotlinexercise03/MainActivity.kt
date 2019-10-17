package com.example.pablohenrique.kotlinexercise03

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private  val tasks : ArrayList<Task> = ArrayList()
    lateinit var taskAdapter : TaskAdapter
    lateinit var editDescription : EditText
    lateinit var editPriority : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editDescription = findViewById(R.id.editDescription)
        editPriority = findViewById(R.id.editPriority)


        if(tasks.isEmpty()) {
            buttonRemove.isEnabled = false
        }

        taskAdapter = TaskAdapter(applicationContext, tasks)
        listView.adapter = taskAdapter

        listView.setOnItemClickListener{
            _, _, position, _ -> tasks.remove(listView.getItemAtPosition(position))
            taskAdapter.notifyDataSetChanged()

            if(tasks.isEmpty()) {
                buttonRemove.isEnabled = false
            }
        }
    }

    fun add(view : View){
            if (tasks.isEmpty()) {
                buttonRemove.isEnabled = true
            }

            val description : String = editDescription.text.toString()
            val priority : Int = editPriority.text.toString().toInt()

            if(validateTask(description, priority)) {
                val task = Task(description, priority)
                tasks.add(task)
                taskAdapter.notifyDataSetChanged()
            }

            sortArrayList()
    }

    fun remove(view : View){
        tasks.removeAt(0)
        taskAdapter.notifyDataSetChanged()

        if (tasks.isEmpty()) {
            buttonRemove.isEnabled = false
        }

    }

    private fun sortArrayList(){
        Collections.sort<Task>(tasks) { task1, task2 ->
            when{
                task1.priority < task2.priority -> -1
                task1.priority > task2.priority -> 1
                else -> 0
            }
        }
        taskAdapter.notifyDataSetChanged()
    }

    private fun validateTask(description : String, priority : Int ) : Boolean {
        if(priority in 1..10){
            for(task in tasks){
                if(description == task.description){
                    Toast.makeText(this, "Task already registred.", Toast.LENGTH_SHORT).show()
                    return false
                }
            }
        }else{
            Toast.makeText(this, "Priority must be between 1 and 10.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}
