package com.apps.fullandroidcourseclassa

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnApply.setOnClickListener {
            val intent = Intent(this, ResturantMenu::class.java)
            startActivity(intent)
            val firstName = etFirstName.text.toString()
            val lastName = etLastName.text.toString()
            val birthDate = etBirthDate.text.toString()
            val country = etCountry.text.toString()
            Toast.makeText(
                this, "Welcome $firstName $lastName, " +
                        "you born at $birthDate in $country", Toast.LENGTH_LONG
            ).show()
        }

        val customList =
            listOf("Eslam", "Salman", "Haneen", "Malak", "Amr", "Safia", "Marwan", "Ahmed", "Ziad")
        val namesAdapter =
            ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, customList)
        spMonths.adapter = namesAdapter
        spMonths.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    this@MainActivity,
                    "You selected ${parent?.getItemAtPosition(position).toString()}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }


        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.miEvent -> Toast.makeText(this, "Event Item", Toast.LENGTH_SHORT).show()
            R.id.miMembers -> Toast.makeText(this, "Members Item", Toast.LENGTH_SHORT).show()
            R.id.miCoreTeam -> Toast.makeText(this, "CoreTeam Item", Toast.LENGTH_SHORT).show()
            R.id.miSettings -> Toast.makeText(this, "Setting Item", Toast.LENGTH_SHORT).show()
            R.id.miLogOut -> {
                val exitAlertDialog = AlertDialog.Builder(this)
                    .setIcon(R.drawable.log_out_icon)
                    .setTitle("Exit")
                    .setCancelable(false)
                    .setMessage("Do you want to exit ?!")
                    .setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                        finish()
                    }
                    .setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->
                        dialogInterface.cancel()
                    }.create()
                exitAlertDialog.show()
            }
        }
        return true
    }

    var pressTwiceToExit = false // false by default
    override fun onBackPressed() {
        if (pressTwiceToExit == true) { // now is true
            finish() // close the app
            super.onBackPressed()
        }
        // var now is false

        pressTwiceToExit = true // reassign to be a true
        Toast.makeText(this, "Press twice to exit", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({
            pressTwiceToExit = false
        }, 3000)


    }
}