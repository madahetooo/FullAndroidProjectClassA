package com.apps.fullandroidcourseclassa

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_base.*

class BaseActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.myName -> Toast.makeText(this, "Eslam", Toast.LENGTH_SHORT).show()
                R.id.myEmailAddress -> Toast.makeText(
                    this,
                    "ieslammedhat@gmail.com",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.myPhone -> Toast.makeText(this, "0341414", Toast.LENGTH_SHORT).show()
                R.id.myPassword -> Toast.makeText(this, "#$@#$%@", Toast.LENGTH_SHORT).show()

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
}