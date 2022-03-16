package com.apps.fullandroidcourseclassa.shoppinglistapp.repository

import com.apps.fullandroidcourseclassa.shoppinglistapp.db.ShoppingDatabase
import com.apps.fullandroidcourseclassa.shoppinglistapp.model.ShoppingItem

class ShoppingRepository(
    private val db: ShoppingDatabase
) {
    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item) //SET
    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)//SET
    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItem()//GET

}