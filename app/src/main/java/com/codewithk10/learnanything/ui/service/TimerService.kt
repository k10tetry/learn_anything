package com.codewithk10.learnanything.ui.service

import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.Handler
import android.os.IBinder
import android.os.Message
import com.codewithk10.learnanything.utils.notification.AppNotification
import com.codewithk10.learnanything.utils.notification.AppNotify
import org.joda.time.LocalTime

class TimerService : Service() {

    companion object {
        const val EXTRA_APP_NOTIFY = "EXTRA_APP_NOTIFY"
        private const val TIMER_RUNNING = 100
        private const val TIMER_FINISHED = 200
    }

    private lateinit var handler: Handler

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (intent == null || intent.extras == null) {
            stopSelf()
            return super.onStartCommand(intent, flags, startId)
        }

        val appNotify = intent.getParcelableExtra<AppNotify>(EXTRA_APP_NOTIFY)

        if (appNotify == null) {
            stopSelf()
            return super.onStartCommand(intent, flags, startId)
        }

        handler = createHandler(appNotify)

        TimeCounter(handler, 60000, 1000).apply {
            start()
        }

        startForeground(
            appNotify.notificationId,
            AppNotification.getInstance(this)
                .getNotification(appNotify, getDisplayTime(60))
        )
        return START_NOT_STICKY
    }

    private fun createHandler(appNotify: AppNotify): Handler = Handler(mainLooper) {
        when (it.what) {
            TIMER_RUNNING -> {
                val notificationManager =
                    getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(
                    appNotify.notificationId,
                    AppNotification.getInstance(this)
                        .getNotification(appNotify, getDisplayTime(it.arg1))
                )
            }
            TIMER_FINISHED -> {
                stopForeground(true)
            }
        }
        true
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    private fun getDisplayTime(seconds: Int): String {
        val local = LocalTime(0, 0, 0).plusSeconds(seconds)
        return String.format(
            "%02d:%02d:%02d",
            local.hourOfDay,
            local.minuteOfHour,
            local.secondOfMinute
        )
    }

    internal class TimeCounter(
        private val handler: Handler,
        millisInFuture: Long,
        countDownInterval: Long
    ) :
        CountDownTimer(millisInFuture, countDownInterval) {

        override fun onTick(p0: Long) {
            val message = Message()
            message.arg1 = p0.div(1000).toInt()
            message.what = TIMER_RUNNING
            handler.sendMessage(message)
        }

        override fun onFinish() {
            val message = Message()
            message.what = TIMER_FINISHED
            handler.sendMessage(message)
        }
    }

}