package com.apps.fullandroidcourseclassa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.apps.fullandroidcourseclassa.R
import kotlinx.android.synthetic.main.activity_resturant_menu.view.*

@Suppress("DEPRECATION")
class RestaurantMenu : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_resturant_menu, container, false)
        view.btnOrder.setOnClickListener {
            val checkedMealRadioButtonId = view.rgMeals.checkedRadioButtonId
            val burger = view.findViewById<RadioButton>(checkedMealRadioButtonId)
            val cheese = view.cbCheese.isChecked
            val onions = view.cbOnions.isChecked
            val salad = view.cbSalad.isChecked
            val totalOrder = "You Ordered Burger with \n" +
                    "${burger.text}" +
                    (if (cheese) "\nCheese " else "") +
                    (if (onions) "\nOnions " else "") +
                    (if (salad) "\nSalad " else "")
            view.tvOrder.text = totalOrder
        }
        return view
    }
}


//           Toast(this).apply {
//               duration = Toast.LENGTH_LONG
//               view = layoutInflater.inflate(R.layout.custom_toast,clToast)
//               show()
//           }