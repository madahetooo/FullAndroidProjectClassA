package com.apps.fullandroidcourseclassa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_todo_list_example.*
import kotlinx.android.synthetic.main.activity_todo_list_example.view.*

class TodoListExample : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_todo_list_example, container, false)
        var todoList = mutableListOf(
            Todo("Buy Pizza", false),
            Todo("Buy Burger", false),
            Todo("Buy Pepsi", false),
            Todo("Study Python", false),
            Todo("Take Android Exam", false),
            Todo("Visit Cairo", false),
            Todo("Solve the android task", false),
            Todo("Give Android Session1", false),
            Todo("Give Android Session2", false),
            Todo("Give Android Session3", false),
            Todo("Give Android Session4", false),
            Todo("Give Android Session5", false),
            Todo("Give Android Session6", false),
            Todo("Give Android Session6", false),
        )
        val adapter = TodoAdapter(todoList)
        view.rvTodo.adapter = adapter
        view.rvTodo.layoutManager = LinearLayoutManager(activity)
        view.btnAddTodo.setOnClickListener {
            val newTodoTitle = view.etTodo.text.toString()
            val newTodo = Todo(newTodoTitle, false)
            todoList.add(newTodo)
//            adapter.notifyDataSetChanged() // Refresh the whole adapter
            adapter.notifyItemInserted(todoList.size - 1)  // Insert only without refresh
            view.etTodo.text.clear()
        }
        return view
    }
}