package com.codewithk10.learnanything.utils.notification

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AppNotify(
    val notificationId: Int,
    val notificationTitle: String,
    val notificationSubTitle: String,
    val notificationReminder: Long,
    val notificationSound: String,
    val notificationSoundUri: String
) : Parcelable