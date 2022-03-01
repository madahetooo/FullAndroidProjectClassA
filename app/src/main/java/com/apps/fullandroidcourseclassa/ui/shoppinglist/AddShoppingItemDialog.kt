package com.apps.fullandroidcourseclassa.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.data.db.entities.ShoppingItem
import kotlinx.android.synthetic.main.dialog_add_shopping_item.*

class AddShoppingItemDialog(context: Context, var addDialogListener: AddDialogListener):AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)

        tvAddShoppingItemAdd.setOnClickListener {
           val shoppingItemName= etShoppingItemDialogName.text.toString()
           val shoppingItemAmount= etShoppingItemDialogAmount.text.toString()
            if (shoppingItemName.isEmpty() ||shoppingItemAmount.isEmpty() ){
                Toast.makeText(context,"Please enter all information",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            var item = ShoppingItem(shoppingItemName,shoppingItemAmount.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }
        tvAddShoppingItemCancel.setOnClickListener {
            cancel()
        }


    }
}