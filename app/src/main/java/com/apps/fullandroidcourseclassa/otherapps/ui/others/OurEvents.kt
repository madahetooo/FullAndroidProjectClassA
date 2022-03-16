package com.apps.fullandroidcourseclassa.otherapps.ui.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.otherapps.adapters.ViewPagerAdapter
import com.apps.fullandroidcourseclassa.databinding.ActivityOurEventsBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class OurEvents : Fragment() {
    private var _binding: ActivityOurEventsBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding= ActivityOurEventsBinding.inflate(inflater, container, false)
        val view = binding.root
        val viewpagger = view.findViewById<ViewPager2>(R.id.viewPagerOfEvents)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayoutOfEvents)
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
        binding.viewPagerOfEvents.adapter = myAdapter
        TabLayoutMediator(tabLayout, viewpagger) { tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()
        binding.tabLayoutOfEvents.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Toast.makeText(activity, "Selected ${tab?.text}", Toast.LENGTH_SHORT).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Toast.makeText(activity, "UnSelected ${tab?.text}", Toast.LENGTH_SHORT).show()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Toast.makeText(activity, "ReSelected ${tab?.text}", Toast.LENGTH_SHORT).show()
            }

        })
        return view
//
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


//        viewPagerOfEvents.orientation = ViewPager2.ORIENTATION_VERTICAL
//        viewPagerOfEvents.beginFakeDrag()
//        viewPagerOfEvents.fakeDragBy(-10f)
//        viewPagerOfEvents.endFakeDrag()
