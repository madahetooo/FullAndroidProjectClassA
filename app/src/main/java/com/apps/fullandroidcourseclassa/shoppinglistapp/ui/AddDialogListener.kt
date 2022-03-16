package com.apps.fullandroidcourseclassa.shoppinglistapp.ui

import com.apps.fullandroidcourseclassa.shoppinglistapp.model.ShoppingItem

interface AddDialogListener {

    fun onAddButtonClicked (item: ShoppingItem)
}