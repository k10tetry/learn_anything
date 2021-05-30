package com.codewithk10.learnanything.utils.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver : BroadcastReceiver() {

    companion object {
        const val EXTRA_NOTIFY_DATA = "EXTRA_NOTIFY_DATA"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let { nonNullIntent ->
            val notify = nonNullIntent.extras?.getSerializable(EXTRA_NOTIFY_DATA) as AppNotify
            context?.let { nonNullContext ->
                AppNotification.getInstance(nonNullContext).createNotification(notify)
            }
        }
    }
}