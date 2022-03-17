package com.apps.fullandroidcourseclassa

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.apps.fullandroidcourseclassa.breakingbadapp.ui.BreakingBad
import com.apps.fullandroidcourseclassa.clothesapp.ui.ClothingApp
import com.apps.fullandroidcourseclassa.databinding.ActivityMotherOfTheAppsBinding
import com.apps.fullandroidcourseclassa.otherapps.ui.base.BaseActivity
import com.apps.fullandroidcourseclassa.otherapps.ui.base.HomeActivity
import com.apps.fullandroidcourseclassa.otherapps.ui.loginsystem.LoginActivity
import com.apps.fullandroidcourseclassa.otherapps.ui.loginsystem.RegistrationActivity
import com.apps.fullandroidcourseclassa.pushnotificationapp.model.PushNotification
import com.apps.fullandroidcourseclassa.pushnotificationapp.ui.CloudMessagingApp
import com.apps.fullandroidcourseclassa.shoppinglistapp.ui.ShoppingActivity
import com.google.firebase.auth.FirebaseAuth

class MotherOfTheApps : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMotherOfTheAppsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding = ActivityMotherOfTheAppsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnTodoListAppAndCalculator.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        binding.btnEventsAndCounter.setOnClickListener {
            val intent = Intent(this, BaseActivity::class.java)
            startActivity(intent)
        }
        binding.btnBreakingBadApp.setOnClickListener {
            val intent = Intent(this, BreakingBad::class.java)
            startActivity(intent)
        }
        binding.btnShoppingListApp.setOnClickListener {
            val intent = Intent(this, ShoppingActivity::class.java)
            startActivity(intent)
        }
        binding.btnClothesApp.setOnClickListener {
            val intent = Intent(this, ClothingApp::class.java)
            startActivity(intent)
        }
        binding.btnPushNotificationApp.setOnClickListener {
            val intent = Intent(this, CloudMessagingApp::class.java)
            startActivity(intent)
        }
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