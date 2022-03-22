package com.apps.fullandroidcourseclassa.todolistapi.service

import com.apps.fullandroidcourseclassa.todolistapi.db.TodoApiData
import retrofit2.Response
import retrofit2.http.GET

interface TodoApi {
    @GET("/todos")
    suspend fun getTodos(): Response<List<TodoApiData>>
}