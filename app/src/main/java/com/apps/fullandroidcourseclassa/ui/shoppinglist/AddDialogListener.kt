package com.apps.fullandroidcourseclassa.ui.shoppinglist

import com.apps.fullandroidcourseclassa.data.db.entities.ShoppingItem

interface AddDialogListener {

    fun onAddButtonClicked (item: ShoppingItem)
}