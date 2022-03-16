package com.apps.fullandroidcourseclassa.onboardingscreen.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.onboardingscreen.ui.utils.OnBoardingScreensItemAdapter
import com.apps.fullandroidcourseclassa.onboardingscreen.model.OnBoardingScreensItem
import com.apps.fullandroidcourseclassa.databinding.ActivityOnboardingScreensBinding

class OnboardingScreens : AppCompatActivity() {
    private lateinit var onBoardingScreensItemAdapter: OnBoardingScreensItemAdapter
    private lateinit var binding: ActivityOnboardingScreensBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingScreensBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupOnBoardingScreenItem()
    }

    private fun setupOnBoardingScreenItem() {
        onBoardingScreensItemAdapter = OnBoardingScreensItemAdapter(
            listOf(
                OnBoardingScreensItem(
                    onBoardingScreensImage = R.drawable.onboardingone,
                    title = "PAY EASY",
                    description = "You can now buy in simple way using your credit card."
                ),
                OnBoardingScreensItem(
                    onBoardingScreensImage = R.drawable.onboardingtwo,
                    title = "XD - UnDraw Plugin",
                    description = "You can now use unDraw Plugin at our XD Application."
                ),
                OnBoardingScreensItem(
                    onBoardingScreensImage = R.drawable.onboardingthree,
                    title = "UI UX Helper",
                    description = "With our UI UX Helper you can organize your app look and make it easy to use"
                ),
            )
        )
        binding.onBoardingViewPager.adapter = onBoardingScreensItemAdapter
    }

    private fun setupIndicators(){

    }
}