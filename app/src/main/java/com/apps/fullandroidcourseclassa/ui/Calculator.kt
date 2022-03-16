package com.apps.fullandroidcourseclassa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.databinding.ActivityCalculatorBinding

class Calculator : Fragment() {
    private var _binding: ActivityCalculatorBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityCalculatorBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.btnAdd.setOnClickListener {
            val firstNumber = binding.etFirstNumber.text.toString().toInt()
            val secondNumber = binding.etSecondNumber.text.toString().toInt()
            val result = firstNumber + secondNumber
            binding.tvResult.text = result.toString()
        }
        binding.btnSubtract.setOnClickListener {
            val firstNumber = binding.etFirstNumber.text.toString().toInt()
            val secondNumber = binding.etSecondNumber.text.toString().toInt()
            val result = firstNumber - secondNumber
            binding.tvResult.text = result.toString()
        }
        binding.btnMultiply.setOnClickListener {
            val firstNumber = binding.etFirstNumber.text.toString().toInt()
            val secondNumber = binding.etSecondNumber.text.toString().toInt()
            val result = firstNumber * secondNumber
            binding.tvResult.text = result.toString()
        }
        binding.btndivide.setOnClickListener {
            val firstNumber = binding.etFirstNumber.text.toString().toInt()
            val secondNumber = binding.etSecondNumber.text.toString().toInt()
            val result = firstNumber / secondNumber
            binding.tvResult.text = result.toString()
        }
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
