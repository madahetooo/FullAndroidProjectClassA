package com.apps.fullandroidcourseclassa.ui

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apps.fullandroidcourseclassa.databinding.ActivityBroadcastReceiverExampleBinding
import com.apps.fullandroidcourseclassa.utils.AirPlaneModeChangedReceiver

class BroadcastReceiverExample : AppCompatActivity() {
    private lateinit var binding:ActivityBroadcastReceiverExampleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBroadcastReceiverExampleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(AirPlaneModeChangedReceiver(),it)

        }
    }
}