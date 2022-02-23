package com.apps.fullandroidcourseclassa.ui

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.apps.fullandroidcourseclassa.R
import kotlinx.android.synthetic.main.activity_main.view.*

@Suppress("DEPRECATION")
class MainActivity : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_main, container, false)
        view.btnApply.setOnClickListener {
            val firstName = view.etFirstName.text.toString()
            val lastName = view.etLastName.text.toString()
            val birthDate = view.etBirthDate.text.toString()
            val country = view.etCountry.text.toString()
            Toast.makeText(
                activity, "Welcome $firstName $lastName, " +
                        "you born at $birthDate in $country", Toast.LENGTH_LONG
            ).show()
        }

        val customList =
            listOf("Eslam", "Salman", "Haneen", "Malak", "Amr", "Safia", "Marwan", "Ahmed", "Ziad")
        val namesAdapter =
            activity?.let { ArrayAdapter<String>(it,
                R.layout.support_simple_spinner_dropdown_item, customList) }
        view.spMonths.adapter = namesAdapter
        view.spMonths.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    activity,
                    "You selected ${parent?.getItemAtPosition(position).toString()}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }


        }
        return view
    }
}

//override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//    menuInflater.inflate(R.menu.app_bar_menu_items, menu)
//    return true
//}
//
//override fun onOptionsItemSelected(item: MenuItem): Boolean {
//    when (item.itemId) {
//        R.id.miEvent -> Toast.makeText(activity, "Event Item", Toast.LENGTH_SHORT).show()
//        R.id.miMembers -> Toast.makeText(activity, "Members Item", Toast.LENGTH_SHORT).show()
//        R.id.miCoreTeam -> Toast.makeText(activity, "CoreTeam Item", Toast.LENGTH_SHORT).show()
//        R.id.miSettings -> Toast.makeText(activity, "Setting Item", Toast.LENGTH_SHORT).show()
//        R.id.miLogOut -> {
//            val exitAlertDialog = AlertDialog.Builder(activity)
//                .setIcon(R.drawable.log_out_icon)
//                .setTitle("Exit")
//                .setCancelable(false)
//                .setMessage("Do you want to exit ?!")
//                .setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
//                    finish()
//                }
//                .setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->
//                    dialogInterface.cancel()
//                }.create()
//            exitAlertDialog.show()
//        }
//    }
//    return true
//}
//
//var pressTwiceToExit = false // false by default
//override fun onBackPressed() {
//    if (pressTwiceToExit == true) { // now is true
//        finish() // close the app
//        super.onBackPressed()
//    }
//    // var now is false
//
//    pressTwiceToExit = true // reassign to be a true
//    Toast.makeText(this, "Press twice to exit", Toast.LENGTH_SHORT).show()
//    Handler().postDelayed({
//        pressTwiceToExit = false
//    }, 3000)
//
//
//}
//}