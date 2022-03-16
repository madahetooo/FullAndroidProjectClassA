package com.apps.fullandroidcourseclassa.otherapps.ui.base

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.databinding.ActivityHomeBinding
import com.apps.fullandroidcourseclassa.firebasefirestoreapp.ui.MainActivity
import com.apps.fullandroidcourseclassa.otherapps.ui.loginsystem.LoginActivity
import com.apps.fullandroidcourseclassa.todolistapp.ui.TodoListExample
import com.apps.fullandroidcourseclassa.otherapps.ui.others.Calculator
import com.apps.fullandroidcourseclassa.otherapps.ui.others.RestaurantMenu
import com.google.firebase.auth.FirebaseAuth

@Suppress("DEPRECATION")
class HomeActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = FirebaseAuth.getInstance()
        setCurrentFragment(TodoListExample())
        val fragmentTodoList = TodoListExample()
        val fragmentRestaurant = RestaurantMenu()
        val fragmentCalculator = Calculator()
        val fragmentProfile = MainActivity()
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.miTodoList -> setCurrentFragment(fragmentTodoList)
                R.id.miRestaurant -> setCurrentFragment(fragmentRestaurant)
                R.id.miCalculator -> setCurrentFragment(fragmentCalculator)
                R.id.miProfile -> setCurrentFragment(fragmentProfile)
            }
            true
        }
        binding.bottomNavigationView.getOrCreateBadge(R.id.miTodoList).apply {
            number = 10
            isVisible = true
        }
    }

   private fun setCurrentFragment(fragment: Fragment)=
       supportFragmentManager.beginTransaction().apply {
           replace(R.id.flFragment,fragment)
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