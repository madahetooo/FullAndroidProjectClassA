package com.apps.fullandroidcourseclassa.ui

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apps.fullandroidcourseclassa.data.AirPlaneModeChangedReceiver
import com.apps.fullandroidcourseclassa.R

class BroadcastReceiverExample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_receiver_example)

        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(AirPlaneModeChangedReceiver(),it)

        }
    }
}