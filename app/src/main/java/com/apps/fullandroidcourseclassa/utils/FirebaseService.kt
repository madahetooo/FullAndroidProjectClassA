package com.apps.fullandroidcourseclassa.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.ui.CloudMessagingApp
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random

class FirebaseService :FirebaseMessagingService() {
    val CHANNEL_ID = "my_channel"

    companion object{
        var sharedPref: SharedPreferences?=null
        var token:String?
        get(){
            return sharedPref?.getString("token","")
        }
        set(value){
            sharedPref?.edit()?.putString("token",value)?.apply()
        }
    }

    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
        token = newToken
    }



    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val intent = Intent(this, CloudMessagingApp::class.java)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationID = Random.nextInt()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel(notificationManager)
        }

        val pendingIntent = PendingIntent.getActivity(this,0,intent,FLAG_ONE_SHOT)
        val notification = NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle(message.data["title"])
            .setContentText(message.data["message"])
            .setSmallIcon(R.drawable.notification_icon)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(notificationID,notification)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager){
        val channelName = "channelName"
        val channel = NotificationChannel(CHANNEL_ID,channelName,IMPORTANCE_HIGH).apply {
            description ="My channel description"
            lightColor = Color.RED
            enableLights(true)
        }
        notificationManager.createNotificationChannel(channel)
    }
}