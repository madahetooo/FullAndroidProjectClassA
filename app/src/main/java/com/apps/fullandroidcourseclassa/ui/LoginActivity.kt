package com.apps.fullandroidcourseclassa.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.apps.fullandroidcourseclassa.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        //Create SharedPreferencesFile
        val sharedPreferences = getSharedPreferences("loginDataFile", MODE_PRIVATE)
        //Using Handler or Editor to get edit access
        val editor = sharedPreferences.edit()

        //Getting the data from the SharedPreferencesFile file with the keys
        val username = sharedPreferences.getString("userName", null)
        val password = sharedPreferences.getString("password", null)
        val isChecked = sharedPreferences.getBoolean("isChecked", false)
        chkRememberMe.isChecked = isChecked
        //Set the saved data on the edit texts
        etUsername.setText(username)
        etPassword.setText(password)

        chkRememberMe.setOnClickListener {
            val userName = etUsername.text.toString()
            val password = etPassword.text.toString()
            val isChecked = chkRememberMe.isChecked
            //Saving the data to the SharedPreferencesFile file
            editor.apply {
                putString("userName", userName)
                putString("password", password)
                putBoolean("isChecked", isChecked)
                apply()
            }

        }
        btnLogin.setOnClickListener {
            val userName = etUsername.text.toString()
            val password = etPassword.text.toString()
            if (userName.isNotEmpty() && password.isNotEmpty()) {
                try {
                    auth.signInWithEmailAndPassword(userName, password)
                    checkedLoggedInState()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()

                } catch (e: Exception) {

                    Toast.makeText(this, e.message, Toast.LENGTH_LONG)
                        .show()

                }

//                CoroutineScope(Dispatchers.IO).launch {
//
//                }

            }
            //Get the data from the edit text

        }
        tvRegister.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun checkedLoggedInState() {
        if (auth.currentUser == null) {
            Toast.makeText(this, "not logged in", Toast.LENGTH_LONG)
                .show()

        }
        else{
            Toast.makeText(this, "Welcome", Toast.LENGTH_LONG)
                .show()
        }
    }
}