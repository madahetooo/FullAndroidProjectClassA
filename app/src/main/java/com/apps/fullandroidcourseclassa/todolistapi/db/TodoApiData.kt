package com.apps.fullandroidcourseclassa.todolistapi.db

data class TodoApiData(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)