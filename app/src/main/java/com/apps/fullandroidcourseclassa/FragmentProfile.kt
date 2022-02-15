package com.apps.fullandroidcourseclassa

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.apps.fullandroidcourseclassa.databinding.FragmentProfileBinding

class FragmentProfile : Fragment(R.layout.fragment_profile) {
    private lateinit var binding: FragmentProfileBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
        binding.tvMyProfile.setText("Eslam")
    }
}