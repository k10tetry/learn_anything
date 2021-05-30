package com.codewithk10.learnanything.utils.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

class AppAlarmUtil(private val context: Context) {

    fun createScheduledAlarm(notify: AppNotify) {
        val pendingIntent = createPendingIntent(notify)

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            notify.notificationReminder,
            AlarmManager.INTERVAL_FIFTEEN_MINUTES,
            pendingIntent
        )
    }

    fun cancelScheduledAlarm(notify: AppNotify) {
        val pendingIntent = createPendingIntent(notify)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
    }

    private fun createPendingIntent(notify: AppNotify): PendingIntent {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(AlarmReceiver.EXTRA_NOTIFY_DATA, notify)
        }
        return PendingIntent.getBroadcast(
            context,
            notify.notificationId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

}