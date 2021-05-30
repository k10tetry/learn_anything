package com.codewithk10.learnanything

import android.app.Application
import com.codewithk10.learnanything.utils.notification.AppNotification
import com.codewithk10.learnanything.utils.notification.AppNotificationChannel

class LearnSkillApp : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        AppNotificationChannel.values().forEach { channel ->
            AppNotification.getInstance(this).createChannel(channel)
        }
    }
}