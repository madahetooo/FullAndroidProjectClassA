package com.apps.fullandroidcourseclassa.ui.loginsystem

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.data.db.model.ProfileInformation
import com.apps.fullandroidcourseclassa.ui.BaseActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    private val profileInformationCollection = Firebase.firestore.collection("profileInformation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        auth = FirebaseAuth.getInstance()
        tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            finish()
        }
        btnRegister.setOnClickListener {
            val emailAddress = etEmailAddress.text.toString()
            val password = etPassword.text.toString()
            val profileInformation = getProfileInformation()
            saveProfileInformationData(profileInformation)
            if (emailAddress.isNotEmpty() && password.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(emailAddress, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val currentUser = auth.currentUser
                            val intent = Intent(this, BaseActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }

    private fun getProfileInformation(): ProfileInformation {
        val fullName = etFullName.text.toString()
        val userName = etUsername.text.toString()
        val emailAddress = etEmailAddress.text.toString()
        val phoneNumber = etPhoneNumber.text.toString()
        val password = etPassword.text.toString()
        return ProfileInformation(fullName, userName, emailAddress, phoneNumber.toInt(), password)
    }

    private fun saveProfileInformationData(profileInformation: ProfileInformation) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                profileInformationCollection.add(profileInformation)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@RegistrationActivity, "Data saved Successfully", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@RegistrationActivity, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}