package com.apps.fullandroidcourseclassa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_home.*

@Suppress("DEPRECATION")
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val fragmentHome = FragmentHome()
        val fragmentApps = FragmentApps()
        val fragmentProfile = FragmentProfile()
        val fragmentSettings = FragmentSettings()
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.miHome -> setCurrentFragment(fragmentHome)
                R.id.miApps -> setCurrentFragment(fragmentApps)
                R.id.miProfile -> setCurrentFragment(fragmentProfile)
                R.id.miSettings -> setCurrentFragment(fragmentSettings)
            }
            true
        }
        bottomNavigationView.getOrCreateBadge(R.id.miApps).apply {
            number = 10
            isVisible = true
        }
    }

   private fun setCurrentFragment(fragment: Fragment)=
       supportFragmentManager.beginTransaction().apply {
           replace(R.id.flFragment,fragment)
           commit()
       }
}