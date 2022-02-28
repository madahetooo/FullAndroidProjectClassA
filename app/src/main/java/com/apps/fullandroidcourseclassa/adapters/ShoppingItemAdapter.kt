package com.apps.fullandroidcourseclassa.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.data.db.entities.ShoppingItem
import com.apps.fullandroidcourseclassa.ui.shoppinglist.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemAdapter(
    var items:List<ShoppingItem>,
    private val viewModel:ShoppingViewModel): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {
        inner class ShoppingViewHolder(itemView:View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {

        val currentShoppingItem = items[position]
        holder.itemView.tvShoppingItemName.text = currentShoppingItem.name
        holder.itemView.tvShoppingItemAmount.text = "${currentShoppingItem.amount}" // because it is INT
        holder.itemView.ivShoppingItemDelete.setOnClickListener {
        viewModel.delete(currentShoppingItem)
        }
        holder.itemView.ivShoppingItemAdd.setOnClickListener {
            currentShoppingItem.amount++
            viewModel.upsert(currentShoppingItem)
        }
        holder.itemView.ivShoppingItemMinus.setOnClickListener {
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