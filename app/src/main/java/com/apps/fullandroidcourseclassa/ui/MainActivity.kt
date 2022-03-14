package com.apps.fullandroidcourseclassa.ui

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.data.db.model.ProfileDetails
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_image_view_example.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

@Suppress("DEPRECATION")
class MainActivity : Fragment() {
    lateinit var auth: FirebaseAuth
    private val profileDetailsCollection = Firebase.firestore.collection("profileDetails")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_main, container, false)
        auth = FirebaseAuth.getInstance()
        subscribeToRealTimeUpdates()
        val btnSaveData = view.findViewById<Button>(R.id.btnSaveProfileData)
        val btnRetrieveData = view.findViewById<Button>(R.id.btnRetrieveProfileData)
        val btnDeleteData = view.findViewById<Button>(R.id.btnDeleteProfileData)
        val btnUpdateData = view.findViewById<Button>(R.id.btnUpdateProfileData)
        btnSaveData.setOnClickListener {
            val profileDetails = getOldProfileDetails()
            saveProfileDetails(profileDetails)
        }
        btnRetrieveData.setOnClickListener {
            retrieveProfileDetails()
        }
        btnDeleteData.setOnClickListener {
            val oldProfileData = getOldProfileDetails()
            deleteProfileDetails(oldProfileData)
        }
        btnUpdateData.setOnClickListener {
            val oldProfileData = getOldProfileDetails()
            val newProfileData = getNewProfileDetails()
            updateProfileDetails(oldProfileData,newProfileData)
        }

        return view
    }
    private fun getOldProfileDetails(): ProfileDetails {
        val oldFirstName = etOldFirstName.text.toString()
        val oldLastName = etOldLastName.text.toString()
        val oldAge = etOldAge.text.toString().toInt()
        return ProfileDetails(oldFirstName,oldLastName,oldAge)
    }

    private fun getNewProfileDetails(): Map<String,Any> {
        val newFirstName = etNewFirstName.text.toString()
        val newLastName = etNewLastName.text.toString()
        val newAge = etNewAge.text.toString()
        val map = mutableMapOf<String,Any>()
        if (newFirstName.isNotEmpty()){
            map ["firstName"] = newFirstName
        }
        if (newLastName.isNotEmpty()){
            map ["lastName"] = newLastName
        }
        if (newAge.isNotEmpty()){
            map ["age"] = newAge.toInt()
        }
    return map
    }

    private fun saveProfileDetails(profileDetails: ProfileDetails) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                profileDetailsCollection.add(profileDetails)
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Data saved Successfully", Toast.LENGTH_LONG).show()
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
                }
            }
        }

    }
    private fun retrieveProfileDetails() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val querySnapshot = profileDetailsCollection.get().await()
                val stringBuilder = StringBuilder()
                for (document in querySnapshot.documents) {
                    val profileDetails = document.toObject<ProfileDetails>()
                    stringBuilder.append("$profileDetails\n")
                }
                withContext(Dispatchers.Main) {
                    tvProfileDetails.text = stringBuilder.toString()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
    private fun subscribeToRealTimeUpdates(){
        profileDetailsCollection.addSnapshotListener { querySnapShot, firebaseFirestoreException ->
            firebaseFirestoreException?.let {
                Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()
                return@addSnapshotListener
            }
            querySnapShot?.let {
                val stringBuilder = StringBuilder()
                for(document in it){
                    val profileDetails = document.toObject<ProfileDetails>()
                    stringBuilder.append("$profileDetails")
                }
                tvProfileDetails.text = stringBuilder.toString()
            }
        }
    }
    private fun deleteProfileDetails(profileDetails: ProfileDetails){
        CoroutineScope(Dispatchers.IO).launch {
            val profileDetailsQuery = profileDetailsCollection
                .whereEqualTo("firstName",profileDetails.firstName)
                .whereEqualTo("lastName",profileDetails.lastName)
                .whereEqualTo("age",profileDetails.age)
                .get()
                .await()
            if (profileDetailsQuery.documents.isNotEmpty()){
                for (document in profileDetailsQuery){
                    try {
                        profileDetailsCollection.document(document.id).delete().await()
                        withContext(Dispatchers.Main){
                            Toast.makeText(requireContext(),"Data Deleted Successfully",Toast.LENGTH_LONG).show()
                        }
                    }catch (e:Exception){
                        withContext(Dispatchers.Main){
                            Toast.makeText(requireContext(),e.message,Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
            else{
                withContext(Dispatchers.Main){
                    Toast.makeText(requireContext(),"No Profile Details Matched the query",Toast.LENGTH_LONG).show()
                }
            }
        }


    }
    private fun updateProfileDetails(oldProfileDetails: ProfileDetails, newProfileDetails:Map<String,Any>){
        CoroutineScope(Dispatchers.IO).launch {
            val profileDetailsQuery = profileDetailsCollection
                .whereEqualTo("firstName",oldProfileDetails.firstName)
                .whereEqualTo("lastName",oldProfileDetails.lastName)
                .whereEqualTo("age",oldProfileDetails.age)
                .get()
                .await()
            if (profileDetailsQuery.documents.isNotEmpty()){
                for (document in profileDetailsQuery){
                    try {
                        profileDetailsCollection.document(document.id).set(newProfileDetails,
                            SetOptions.merge()).await()
                        withContext(Dispatchers.Main){
                            Toast.makeText(requireContext(),"Data Updated Successfully",Toast.LENGTH_LONG).show()
                        }
                    }catch (e:Exception){
                        withContext(Dispatchers.Main){
                            Toast.makeText(requireContext(),e.message,Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
            else{
                withContext(Dispatchers.Main){
                    Toast.makeText(requireContext(),"No Profile Details Matched the query",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}