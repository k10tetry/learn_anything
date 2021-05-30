package com.codewithk10.learnanything.utils.notification

import java.io.Serializable

data class AppNotify(
    val notificationId: Int,
    val notificationTitle: String,
    val notificationSubTitle: String,
    val notificationReminder: Long,
    val notificationSound: String,
    val notificationSoundUri: String
) : Serializable