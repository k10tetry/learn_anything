package com.codewithk10.learnanything.data.db.converter

import androidx.room.TypeConverter
import org.joda.time.LocalTime

class LocalTimeConverter {

    @TypeConverter
    fun localTimeToMillis(localTime: LocalTime): Int {
        return localTime.millisOfDay
    }

    @TypeConverter
    fun millisToLocalTime(millis: Int): LocalTime {
        return LocalTime.fromMillisOfDay(millis.toLong())
    }

}