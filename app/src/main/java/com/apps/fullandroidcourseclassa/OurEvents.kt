package com.apps.fullandroidcourseclassa

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_calculator.*
import kotlinx.android.synthetic.main.activity_our_events.*

class OurEvents : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_our_events)
        val images = listOf(
            R.drawable.speakerone,
            R.drawable.speakertwo,
            R.drawable.speakerthree,
            R.drawable.speakerfour,
            R.drawable.speakerfive,
            R.drawable.speakersix,
            R.drawable.eventone,
            R.drawable.eventtwo,
        )
        val myAdapter = ViewPagerAdapter(images)
        viewPagerOfEvents.adapter = myAdapter
        TabLayoutMediator(tabLayoutOfEvents, viewPagerOfEvents) { tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()
        tabLayoutOfEvents.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Toast.makeText(this@OurEvents, "Selected ${tab?.text}", Toast.LENGTH_SHORT).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@OurEvents, "UnSelected ${tab?.text}", Toast.LENGTH_SHORT).show()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@OurEvents, "ReSelected ${tab?.text}", Toast.LENGTH_SHORT).show()
            }

        })


//        viewPagerOfEvents.orientation = ViewPager2.ORIENTATION_VERTICAL
//        viewPagerOfEvents.beginFakeDrag()
//        viewPagerOfEvents.fakeDragBy(-10f)
//        viewPagerOfEvents.endFakeDrag()


    }
}