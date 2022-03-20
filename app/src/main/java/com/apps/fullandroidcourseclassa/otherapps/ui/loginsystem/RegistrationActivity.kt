package com.apps.fullandroidcourseclassa.otherapps.ui.loginsystem

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.apps.fullandroidcourseclassa.MotherOfTheApps
import com.apps.fullandroidcourseclassa.databinding.ActivityRegistrationBinding
import com.apps.fullandroidcourseclassa.firebasefirestoreapp.model.ProfileInformation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val TAG = "RegistrationActivity"

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    lateinit var auth: FirebaseAuth
    private val profileInformationCollection = Firebase.firestore.collection("profileInformation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = FirebaseAuth.getInstance()
        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnRegister.setOnClickListener {
            val emailAddress = binding.etEmailAddress.text.toString()
            val password = binding.etPassword.text.toString()
            val profileInformation = getProfileInformation()
            saveProfileInformationData(profileInformation)
            if (emailAddress.isNotEmpty() && password.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(emailAddress, password)
                    .addOnSuccessListener {
                        val currentUser = auth.currentUser
                        val intent = Intent(this, MotherOfTheApps::class.java)
                        startActivity(intent)
                        Log.d(TAG, "Authentication Succeed")
                        finish()
                    }.addOnFailureListener {
                        Log.d(TAG, "Authentication Failed ${it.message}")
                    }
            }
        }
    }
    private fun getProfileInformation(): ProfileInformation {
        val fullName = binding.etFullName.text.toString()
        val userName = binding.etUsername.text.toString()
        val emailAddress = binding.etEmailAddress.text.toString()
        val phoneNumber = binding.etPhoneNumber.text.toString()
        val password = binding.etPassword.text.toString()
        return ProfileInformation(fullName, userName, emailAddress, phoneNumber.toInt(), password)
    }
    private fun saveProfileInformationData(profileInformation: ProfileInformation) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                profileInformationCollection.add(profileInformation)
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@RegistrationActivity,
                        "Data saved Successfully",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@RegistrationActivity, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}