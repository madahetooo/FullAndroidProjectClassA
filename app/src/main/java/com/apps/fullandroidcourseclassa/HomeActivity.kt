package com.apps.fullandroidcourseclassa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_home.*

@Suppress("DEPRECATION")
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setCurrentFragment(TodoListExample())
        val fragmentTodoList = TodoListExample()
        val fragmentRestaurant = RestaurantMenu()
        val fragmentCalculator = Calculator()
        val fragmentProfile = MainActivity()
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.miTodoList -> setCurrentFragment(fragmentTodoList)
                R.id.miRestaurant -> setCurrentFragment(fragmentRestaurant)
                R.id.miCalculator -> setCurrentFragment(fragmentCalculator)
                R.id.miProfile -> setCurrentFragment(fragmentProfile)
            }
            true
        }
        bottomNavigationView.getOrCreateBadge(R.id.miTodoList).apply {
            number = 10
            isVisible = true
        }
    }

   private fun setCurrentFragment(fragment: Fragment)=
       supportFragmentManager.beginTransaction().apply {
           replace(R.id.flFragment,fragment)
           commit()
       }
}