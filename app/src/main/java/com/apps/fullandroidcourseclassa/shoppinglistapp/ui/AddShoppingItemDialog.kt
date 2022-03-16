package com.apps.fullandroidcourseclassa.shoppinglistapp.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.apps.fullandroidcourseclassa.shoppinglistapp.model.ShoppingItem
import com.apps.fullandroidcourseclassa.databinding.DialogAddShoppingItemBinding

class AddShoppingItemDialog(context: Context, var addDialogListener: AddDialogListener):AppCompatDialog(context) {
    private lateinit var binding: DialogAddShoppingItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAddShoppingItemBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.tvAddShoppingItemAdd.setOnClickListener {
           val shoppingItemName= binding.etShoppingItemDialogName.text.toString()
           val shoppingItemAmount= binding.etShoppingItemDialogAmount.text.toString()
            if (shoppingItemName.isEmpty() ||shoppingItemAmount.isEmpty() ){
                Toast.makeText(context,"Please enter all information",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            var item = ShoppingItem(shoppingItemName,shoppingItemAmount.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }
        binding.tvAddShoppingItemCancel.setOnClickListener {
            cancel()
        }


    }
}