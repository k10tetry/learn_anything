package com.codewithk10.learnanything.data.db.converter

import androidx.room.TypeConverter
import com.codewithk10.learnanything.utils.notification.AppNotify
import com.google.gson.Gson

class AppNotifyConvertor {

    @TypeConverter
    fun appNotifyToString(appNotify: AppNotify): String {
        return Gson().toJson(appNotify)
    }

    @TypeConverter
    fun stringToAppNotify(value: String): AppNotify {
        return Gson().fromJson(value, AppNotify::class.java)
    }

}