package com.apps.fullandroidcourseclassa.todolistapi.ui.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.apps.fullandroidcourseclassa.databinding.ItemTodoApiBinding
import com.apps.fullandroidcourseclassa.todolistapi.db.TodoApiData

class TodoApiAdapter :RecyclerView.Adapter<TodoApiAdapter.TodoApiViewHolder>() {
    inner class TodoApiViewHolder(val binding: ItemTodoApiBinding):RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object :DiffUtil.ItemCallback<TodoApiData>(){
        override fun areItemsTheSame(oldItem: TodoApiData, newItem: TodoApiData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodoApiData, newItem: TodoApiData): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)
    var todos: List<TodoApiData>
    get() = differ.currentList
    set(value){
        differ.submitList(value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoApiViewHolder {
        return TodoApiViewHolder(ItemTodoApiBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TodoApiViewHolder, position: Int) {
        holder.binding.apply {
            val singleTodo = todos[position]
            tvTodoApiTitle.text = singleTodo.title
            cbTodoApiDone.isChecked = singleTodo.completed
        }

    }

    override fun getItemCount() = todos.size
}