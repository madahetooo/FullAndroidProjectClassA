package com.apps.fullandroidcourseclassa.shoppinglistapp.ui

import androidx.lifecycle.ViewModel
import com.apps.fullandroidcourseclassa.shoppinglistapp.model.ShoppingItem
import com.apps.fullandroidcourseclassa.shoppinglistapp.repository.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(private val repository: ShoppingRepository) :ViewModel() {

    fun upsert(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }
    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }
    fun getAllShoppingItems() = repository.getAllShoppingItems()
}