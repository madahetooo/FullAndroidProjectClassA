package com.apps.fullandroidcourseclassa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apps.fullandroidcourseclassa.databinding.ActivityCalculatorBinding

class Calculator : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

    }
}