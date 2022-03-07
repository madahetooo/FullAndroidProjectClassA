package com.apps.fullandroidcourseclassa.ui.loginsystem

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.ui.HomeActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*

const val REQUEST_CODE_SIGN_IN = 0

@Suppress("DEPRECATION")
class LoginActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
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
                auth.signInWithEmailAndPassword(userName, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val currentUser = auth.currentUser
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
        tvRegister.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnSignInWithGoogle.setOnClickListener {
            val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                .requestEmail()
                .build()

            val signInClient = GoogleSignIn.getClient(this, options)
            signInClient.signInIntent.also {
                startActivityForResult(it, REQUEST_CODE_SIGN_IN)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_SIGN_IN) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(data).result
            account?.let {
                googleAuthForFirebase(account)
            }
        }


    }

    private fun googleAuthForFirebase(gmailAccount: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(gmailAccount.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val currentUser = auth.currentUser
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                } else {
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}


//Create SharedPreferencesFile



