package com.apps.fullandroidcourseclassa.otherapps.ui.utils

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apps.fullandroidcourseclassa.databinding.ActivityBroadcastReceiverExampleBinding

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