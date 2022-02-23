package com.apps.fullandroidcourseclassa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.apps.fullandroidcourseclassa.R
import kotlinx.android.synthetic.main.activity_calculator.view.*

class Calculator : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_calculator, container, false)
        view.btnAdd.setOnClickListener {
            val firstNumber = view.etFirstNumber.text.toString().toInt()
            val secondNumber = view.etSecondNumber.text.toString().toInt()
            val result = firstNumber + secondNumber
            view.tvResult.text = result.toString()
        }
        view.btnSubtract.setOnClickListener {
            val firstNumber = view.etFirstNumber.text.toString().toInt()
            val secondNumber = view.etSecondNumber.text.toString().toInt()
            val result = firstNumber - secondNumber
            view.tvResult.text = result.toString()
        }
        view.btnMultiply.setOnClickListener {
            val firstNumber = view.etFirstNumber.text.toString().toInt()
            val secondNumber = view.etSecondNumber.text.toString().toInt()
            val result = firstNumber * secondNumber
            view.tvResult.text = result.toString()
        }
        view.btndivide.setOnClickListener {
            val firstNumber = view.etFirstNumber.text.toString().toInt()
            val secondNumber = view.etSecondNumber.text.toString().toInt()
            val result = firstNumber / secondNumber
            view.tvResult.text = result.toString()
        }
        return view
    }
}
