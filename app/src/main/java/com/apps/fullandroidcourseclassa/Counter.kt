package com.apps.fullandroidcourseclassa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_counter.*

class Counter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)
        var count = 0
        btnCount.setOnClickListener {
            count++
            tvCount.text = " Lets Count together $count"
        }
    }
}