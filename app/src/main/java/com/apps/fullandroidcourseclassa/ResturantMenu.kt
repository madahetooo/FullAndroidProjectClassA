package com.apps.fullandroidcourseclassa

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_resturant_menu.*
import kotlinx.android.synthetic.main.custom_toast.*

@Suppress("DEPRECATION")
class ResturantMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resturant_menu)
        btnOrder.setOnClickListener {
//        val checkedMealRadioButtonId = rgMeals.checkedRadioButtonId
//            val burger = findViewById<RadioButton>(checkedMealRadioButtonId)
//            val cheese = cbCheese.isChecked
//            val onions = cbOnions.isChecked
//            val salad = cbSalad.isChecked
//            val totalOrder = "You Ordered Burger with \n"+
//                    "${burger.text}"+
//                    (if (cheese)"\nCheese " else "")+
//                    (if (onions)"\nOnions " else "")+
//                    (if (salad)"\nSalad " else "")
//            tvOrder.text = totalOrder

           Toast(this).apply {
               duration = Toast.LENGTH_LONG
               view = layoutInflater.inflate(R.layout.custom_toast,clToast)
               show()
           }

        }
    }
}