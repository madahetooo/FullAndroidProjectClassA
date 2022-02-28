package com.apps.fullandroidcourseclassa.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.apps.fullandroidcourseclassa.R
import kotlinx.android.synthetic.main.activity_notification_example.*

class NotificationExample : AppCompatActivity() {
    val CHANNEL_ID = "gdgEventTicketChannelID"
    val CHANNEL_NAME = "gdgEventChannelName"
    val NOTIFICATION_ID = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_example)
        createNotificationChannel()


        val intent = Intent(this, NotificationExample::class.java)
        val pendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val eventTicketNotification = NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("YOUR TICKET")
            .setContentText("You are invited to attend our event")
            .setSmallIcon(R.drawable.notification_icon)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        val eventTicketNotificationManger = NotificationManagerCompat.from(this)
        btnShowNotification.setOnClickListener {
            eventTicketNotificationManger.notify(NOTIFICATION_ID,eventTicketNotification)
        }

    }

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                lightColor = Color.RED
                enableLights(true)
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}