package com.apps.fullandroidcourseclassa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.apps.fullandroidcourseclassa.data.db.ProfileInformation
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_image_view_example.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RetrieveProfileInformation : AppCompatActivity() {
    private val profileInformationCollection = Firebase.firestore.collection("profileInformation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrieve_profile_information)
        btnRetrieveData.setOnClickListener {
            retrieveProfileInformation()
        }
    }
    private fun retrieveProfileInformation() = CoroutineScope(Dispatchers.IO).launch {
        try {
            val querySnapshot = profileInformationCollection.get()
            val stringBuilder = StringBuilder()
            stringBuilder.append("$querySnapshot\n")
            for (document in querySnapshot){
                val profileInformation = document.toObject<ProfileInformation>()
                withContext(Dispatchers.Main) {
                    tvProfileInformation.text = querySnapshot.toString()
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@RetrieveProfileInformation, e.message, Toast.LENGTH_LONG).show()
            }

        }
    }
}