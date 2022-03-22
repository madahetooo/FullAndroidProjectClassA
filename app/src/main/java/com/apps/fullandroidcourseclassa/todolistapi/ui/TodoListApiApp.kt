package com.apps.fullandroidcourseclassa.todolistapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.databinding.ActivityTodoListApiAppBinding
import com.apps.fullandroidcourseclassa.todolistapi.service.RetrofitInstanceTodoApi
import com.apps.fullandroidcourseclassa.todolistapi.ui.utils.TodoApiAdapter
import retrofit2.HttpException
import java.io.IOException

const val TAG ="TodoListApiApp"
class TodoListApiApp : AppCompatActivity() {
    private lateinit var binding: ActivityTodoListApiAppBinding
    private lateinit var todoApiAdapter: TodoApiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoListApiAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //CallingSetup RecyclerView Fun
        setupRecyclerView()

        //STOP WHEN WE CLOSE THE APP FROM THE BACKGROUND
        lifecycleScope.launchWhenCreated {
            binding.progressBarTodosApi.isVisible = true
            val response = try {
                RetrofitInstanceTodoApi.api.getTodos()
            }catch (e:IOException){
                Log.d(TAG,"IOException,You might have no internet connection")
                binding.progressBarTodosApi.isVisible = false
                return@launchWhenCreated
            }
            catch (e:HttpException){
                Log.d(TAG,"HttpException,unexpected response")
                binding.progressBarTodosApi.isVisible = false
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body()!=null){
                todoApiAdapter.todos = response.body()!!
            }else{
                Log.d(TAG,"Response Not successful")
            }
            binding.progressBarTodosApi.isVisible = false
        }
    }


    private fun setupRecyclerView() = binding.rvTodoApi.apply {
        todoApiAdapter = TodoApiAdapter() // Hold Data
        adapter = todoApiAdapter // Set data to RecyclerView Adapter
        layoutManager = LinearLayoutManager(this@TodoListApiApp)
    }
}