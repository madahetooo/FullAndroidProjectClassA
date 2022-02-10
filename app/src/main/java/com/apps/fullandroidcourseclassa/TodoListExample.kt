package com.apps.fullandroidcourseclassa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_todo_list_example.*

class TodoListExample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list_example)
        var todoList = mutableListOf(
            Todo("Buy Pizza",false),
            Todo("Buy Burger",false),
            Todo("Buy Pepsi",false),
            Todo("Study Python",false),
            Todo("Take Android Exam",false),
            Todo("Visit Cairo",false),
            Todo("Solve the android task",false),
            Todo("Give Android Session1",false),
            Todo("Give Android Session2",false),
            Todo("Give Android Session3",false),
            Todo("Give Android Session4",false),
            Todo("Give Android Session5",false),
            Todo("Give Android Session6",false),
            Todo("Give Android Session6",false),
        )
        val adapter = TodoAdapter(todoList)
        rvTodo.adapter = adapter
        rvTodo.layoutManager = LinearLayoutManager(this)
        btnAddTodo.setOnClickListener {
            val newTodoTitle = etTodo.text.toString()
            val newTodo = Todo(newTodoTitle,false)
            todoList.add(newTodo)
//            adapter.notifyDataSetChanged() // Refresh the whole adapter
            adapter.notifyItemInserted(todoList.size-1)  // Insert only without refresh
            etTodo.text.clear()

        }
    }
}