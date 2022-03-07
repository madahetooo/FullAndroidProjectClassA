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
import com.apps.fullandroidcourseclassa.ui.loginsystem.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_base.*

class BaseActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        auth = FirebaseAuth.getInstance()
        setCurrentFragment(Counter())
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.miCounter -> {
                    val counterFragment = Counter()
                    setCurrentFragment(counterFragment)
                    drawerLayout.close()

                }
                R.id.miImageExample -> {
                    val imageViewExampleFragment = ImageViewExample()
                    setCurrentFragment(imageViewExampleFragment)
                    drawerLayout.close()
                }

                R.id.miOurEvents -> {
                    val ourEventsFragment = OurEvents()
                    setCurrentFragment(ourEventsFragment)
                    drawerLayout.close()
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