package com.apps.fullandroidcourseclassa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.data.db.ProfileInformation
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_image_view_example.*
import kotlinx.coroutines.*

class ImageViewExample : Fragment() {
    private val profileInformationCollection = Firebase.firestore.collection("profileInformation")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_image_view_example, container, false)

        return view
    }

}

