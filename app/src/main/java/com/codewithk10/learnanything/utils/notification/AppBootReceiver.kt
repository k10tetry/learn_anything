package com.codewithk10.learnanything.utils.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.codewithk10.learnanything.data.db.AppDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class AppBootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            readAllNotifications(it)
        }
    }

    private fun readAllNotifications(context: Context) {
        val alarmService = AppAlarmService(context)
        AppDatabase.getDatabase(context).skillDao().getAllSkillNotifications()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ appNotify ->
                appNotify.forEach {
                    alarmService.createScheduledAlarm(it)
                }
            }, {
                it.printStackTrace()
            })
    }
}