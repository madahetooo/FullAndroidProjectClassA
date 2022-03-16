package com.apps.fullandroidcourseclassa.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.databinding.ActivitySplashScreenBinding
import com.apps.fullandroidcourseclassa.ui.loginsystem.LoginActivity

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {
    private lateinit var binding:ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()
        Handler().postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}