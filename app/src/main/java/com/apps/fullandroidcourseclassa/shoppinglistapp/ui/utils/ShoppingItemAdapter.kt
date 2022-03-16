package com.apps.fullandroidcourseclassa.shoppinglistapp.ui.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.fullandroidcourseclassa.shoppinglistapp.model.ShoppingItem
import com.apps.fullandroidcourseclassa.databinding.ShoppingItemBinding
import com.apps.fullandroidcourseclassa.shoppinglistapp.ui.ShoppingViewModel

class ShoppingItemAdapter(var items:List<ShoppingItem>, private val viewModel: ShoppingViewModel): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {
        inner class ShoppingViewHolder(val binding:ShoppingItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        return ShoppingViewHolder(ShoppingItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {

        val currentShoppingItem = items[position]
        holder.binding.tvShoppingItemName.text = currentShoppingItem.name
        holder.binding.tvShoppingItemAmount.text = "${currentShoppingItem.amount}" // because it is INT
        holder.binding.ivShoppingItemDelete.setOnClickListener {
        viewModel.delete(currentShoppingItem)
        }
        holder.binding.ivShoppingItemAdd.setOnClickListener {
            currentShoppingItem.amount++
            viewModel.upsert(currentShoppingItem)
        }
        holder.binding.ivShoppingItemMinus.setOnClickListener {
            if (currentShoppingItem.amount>0){
                currentShoppingItem.amount--
                viewModel.upsert(currentShoppingItem)
            }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}