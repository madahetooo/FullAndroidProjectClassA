package com.apps.fullandroidcourseclassa.ui

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.databinding.ActivityBaseBinding
import com.apps.fullandroidcourseclassa.ui.loginsystem.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class BaseActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityBaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = FirebaseAuth . getInstance ()
        setCurrentFragment(Counter())
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.miCounter -> {
                    val counterFragment = Counter()
                    setCurrentFragment(counterFragment)
                    binding.drawerLayout.close()

                }
                R.id.miImageExample -> {
                    val imageViewExampleFragment = ImageViewExample()
                    setCurrentFragment(imageViewExampleFragment)
                    binding.drawerLayout.close()
                }

                R.id.miOurEvents -> {
                    val ourEventsFragment = OurEvents()
                    setCurrentFragment(ourEventsFragment)
                    binding.drawerLayout.close()
                }
                R.id.miLogOut -> {
                    val exitAlertDialog = AlertDialog.Builder(this)
                        .setIcon(R.drawable.log_out_icon)
                        .setTitle("Exit")
                        .setCancelable(false)
                        .setMessage("Do you want to exit ?!")
                        .setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                            auth.signOut()
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        .setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->
                            dialogInterface.cancel()
                        }
                    val alertDialog = exitAlertDialog.create()
                    alertDialog.show()
                }

            }
            true
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.clContent, fragment)
            commit()
        }

    override fun onBackPressed() {
//        super.onBackPressed()
        val exitAlertDialog = AlertDialog.Builder(this)
            .setIcon(R.drawable.log_out_icon)
            .setTitle("Exit")
            .setCancelable(false)
            .setMessage("Do you want to exit ?!")
            .setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                auth.signOut()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            .setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.cancel()
            }
        val alertDialog = exitAlertDialog.create()
        alertDialog.show()
    }

}