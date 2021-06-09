package com.codewithk10.learnanything.utils.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.codewithk10.learnanything.data.db.AppDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.joda.time.DateTime
import org.joda.time.Days

class AppBootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            readAllNotifications(it)
        }
    }

    private fun readAllNotifications(context: Context) {
        AppDatabase.getDatabase(context).skillDao().getAllSkillNotifications()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                reCreateNotifications(context, it)
            }, {
                it.printStackTrace()
            })
    }

    private fun reCreateNotifications(context: Context, appNotify: List<AppNotify>) {
        val alarmService = AppAlarmService(context)
        appNotify.forEach {
            alarmService.createScheduledAlarm(getNewNotification(it))
        }
    }

    /**
     * This method will skip passed time and set upcoming alarm
     * as scheduled for the skill
     */
    private fun getNewNotification(notify: AppNotify): AppNotify {
        val notificationDateTime = DateTime(notify.notificationReminder)
        if (notificationDateTime.isBeforeNow) {
            val nextTimeMultiplier = Days.daysBetween(notificationDateTime, DateTime.now())
                .dividedBy(AlarmReceiver.NEXT_ALARM_IN_DAYS).plus(Days.ONE).days
            val newNotificationTime = notificationDateTime.plusDays(
                AlarmReceiver.NEXT_ALARM_IN_DAYS.times(nextTimeMultiplier)
            ).millis
            return notify.copy(
                notificationReminder = newNotificationTime
            )
        }
        return notify
    }
}