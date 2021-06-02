package com.codewithk10.learnanything.utils.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.joda.time.DateTime

class AlarmReceiver : BroadcastReceiver() {

    companion object {
        const val EXTRA_NOTIFY_DATA = "EXTRA_NOTIFY_DATA"
        const val EXTRA_NOTIFY_BUNDLE = "EXTRA_NOTIFY_BUNDLE"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null && context != null) {
            intent.getBundleExtra(EXTRA_NOTIFY_BUNDLE)?.let {
                val notify = it.getParcelable<AppNotify>(EXTRA_NOTIFY_DATA)
                notify?.let {
                    AppNotification.getInstance(context).createNotification(notify)
                    reScheduleAlarm(context, notify)
                }
            }
        }
    }

    private fun reScheduleAlarm(context: Context, notify: AppNotify) {
        val nextNotify = notify.copy(
            notificationReminder = DateTime(notify.notificationReminder).plusDays(1).millis
        )
        AppAlarmService(context).apply {
            createScheduledAlarm(nextNotify)
        }
    }
}