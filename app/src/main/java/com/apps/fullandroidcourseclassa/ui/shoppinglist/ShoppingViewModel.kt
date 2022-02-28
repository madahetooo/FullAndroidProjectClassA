package com.apps.fullandroidcourseclassa.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.apps.fullandroidcourseclassa.data.db.entities.ShoppingItem
import com.apps.fullandroidcourseclassa.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(private val repository: ShoppingRepository) :ViewModel() {

    fun upsert(item:ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }
    fun delete(item:ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }
    fun getAllShoppingItems() = repository.getAllShoppingItems()
}