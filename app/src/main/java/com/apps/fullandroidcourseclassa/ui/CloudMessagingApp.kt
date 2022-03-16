package com.apps.fullandroidcourseclassa.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.data.db.model.NotificationData
import com.apps.fullandroidcourseclassa.data.db.model.PushNotification
import com.apps.fullandroidcourseclassa.data.db.RetrofitInstance
import com.apps.fullandroidcourseclassa.databinding.ActivityClothingAppBinding
import com.apps.fullandroidcourseclassa.databinding.ActivityCloudMessagingAppBinding
import com.apps.fullandroidcourseclassa.utils.FirebaseService
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val TOPIC = "/topics/myTopic"
class CloudMessagingApp : AppCompatActivity() {

    val TAG = "CloudMessagingApp"
    private lateinit var binding: ActivityCloudMessagingAppBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCloudMessagingAppBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        FirebaseService.sharedPref = getSharedPreferences("sharedPrefFile", Context.MODE_PRIVATE)
        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            FirebaseService.token = it
            binding.etNotificationToken.setText(it)
        }
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
        binding.btnSendNotification.setOnClickListener {
         val notificationTitle =   binding.etNotificationTitle.text.toString()
         val notificationMessage =   binding.etNotificationMessage.text.toString()
         val recipientToken =   binding.etNotificationToken.text.toString()
            if (notificationTitle.isNotEmpty() && notificationMessage.isNotEmpty() && recipientToken.isNotEmpty()){
                PushNotification(NotificationData(notificationTitle,notificationMessage), recipientToken).also {
                    sendNotification(it)
                }
            }
        }
    }
    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
            try {
                val response  = RetrofitInstance.api.postNotification(notification)
                if (response.isSuccessful){
                    Log.d(TAG,"${Gson().toJson(response)}")
                }else{
                    Log.d(TAG,response.errorBody().toString())
                }

            }
            catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@CloudMessagingApp,e.message,Toast.LENGTH_LONG).show()
                }
            }
        }
    }
