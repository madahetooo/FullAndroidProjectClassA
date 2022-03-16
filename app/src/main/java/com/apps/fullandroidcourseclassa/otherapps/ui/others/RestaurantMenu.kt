package com.apps.fullandroidcourseclassa.otherapps.ui.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.databinding.ActivityResturantMenuBinding

@Suppress("DEPRECATION")
class RestaurantMenu : Fragment() {
    private var _binding: ActivityResturantMenuBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= ActivityResturantMenuBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.btnOrder.setOnClickListener {
            val checkedMealRadioButtonId = binding.rgMeals.checkedRadioButtonId
            val burger = view.findViewById<RadioButton>(checkedMealRadioButtonId)
            val cheese = binding.cbCheese.isChecked
            val onions = binding.cbOnions.isChecked
            val salad = binding.cbSalad.isChecked
            val totalOrder = "You Ordered Burger with \n" +
                    "${burger.text}" +
                    (if (cheese) "\nCheese " else "") +
                    (if (onions) "\nOnions " else "") +
                    (if (salad) "\nSalad " else "")
            binding.tvOrder.text = totalOrder
        }
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


//           Toast(this).apply {
//               duration = Toast.LENGTH_LONG
//               view = layoutInflater.inflate(R.layout.custom_toast,clToast)
//               show()
//           }