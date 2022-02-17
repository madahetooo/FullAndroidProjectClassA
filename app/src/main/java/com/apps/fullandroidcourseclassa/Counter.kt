package com.apps.fullandroidcourseclassa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_counter.*
import kotlinx.android.synthetic.main.activity_counter.view.*

class Counter : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_counter, container, false)
        var count = 0
        view.btnCount.setOnClickListener {
            count++
            view.tvCount.text = " Lets Count together $count"
        }
        return view
    }
}
