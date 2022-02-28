package com.apps.fullandroidcourseclassa.data.repositories

import com.apps.fullandroidcourseclassa.data.db.ShoppingDatabase
import com.apps.fullandroidcourseclassa.data.db.entities.ShoppingItem

class ShoppingRepository(
    private val db: ShoppingDatabase
) {
    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item) //SET
    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)//SET
    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItem()//GET

}