package com.codewithk10.learnanything.utils.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.ui.dashboard.DashboardActivity
import java.lang.ref.WeakReference

class AppNotification(private val context: Context) {

    companion object {
        private var INSTANCE: WeakReference<AppNotification>? = null
        fun getInstance(context: Context): AppNotification {
            return INSTANCE?.get() ?: synchronized(this) {
                val instance = AppNotification(context)
                INSTANCE = WeakReference<AppNotification>(instance)
                instance
            }
        }
    }

    fun createChannel(channel: AppNotificationChannel) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val notificationChannel =
                NotificationChannel(channel.channelId, channel.channelName, importance)
            notificationChannel.description = channel.channelDescription
            val notificationManager =
                context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    fun createNotification(notify: AppNotify) {
        val intent = DashboardActivity.getInstance(context).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification =
            NotificationCompat.Builder(context, AppNotificationChannel.REMINDER.channelId)
                .setContentTitle(notify.notificationTitle)
                .setContentText(notify.notificationSubTitle)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setDefaults(Notification.DEFAULT_ALL)
                .build()
        val notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notify.notificationId, notification)
    }

    fun getNotification(notify: AppNotify, displayTime: String): Notification {
        val intent = DashboardActivity.getInstance(context).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        return NotificationCompat.Builder(context, AppNotificationChannel.REMINDER.channelId)
            .setContentTitle(notify.notificationTitle)
            .setContentText(displayTime)
            .setAutoCancel(true)
            .setShowWhen(false)
            .setOngoing(true)
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setDefaults(Notification.DEFAULT_ALL)
            .build()
    }

}