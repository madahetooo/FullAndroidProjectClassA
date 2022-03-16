package com.apps.fullandroidcourseclassa.otherapps.ui.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.databinding.ActivityCounterBinding

class Counter : Fragment() {
    private var _binding: ActivityCounterBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityCounterBinding.inflate(inflater, container, false)
        val view = binding.root
        var count = 0
        binding.btnCount.setOnClickListener {
            count++
            binding.tvCount.text = " Lets Count together $count"
        }
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
