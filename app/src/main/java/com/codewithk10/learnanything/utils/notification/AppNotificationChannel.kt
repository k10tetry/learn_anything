package com.codewithk10.learnanything.utils.notification

enum class AppNotificationChannel(
    val channelId: String,
    val channelName: String,
    val channelDescription: String
) {
    REMINDER("Reminder", "Skill reminder notifications", "channel-reminder")
}