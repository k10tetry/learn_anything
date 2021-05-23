package com.codewithk10.learnanything.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codewithk10.learnanything.ui.skill.data.CategoryData
import com.codewithk10.learnanything.ui.skill.data.TargetData
import org.joda.time.LocalTime

@Entity(tableName = "skill")
data class Skill(
    @ColumnInfo(name = "skill_title") val skillTitle: String,
    @ColumnInfo(name = "skill_note") val skillNote: String,
    @ColumnInfo(name = "notification_reminder") val notificationReminder: LocalTime,
    @ColumnInfo(name = "notification_sound") val notificationSound: String?,
    @ColumnInfo(name = "notification_sound_uri") val notificationSoundUri: String?,
    @ColumnInfo(name = "skill_target") val skillTarget: TargetData,
    @ColumnInfo(name = "skill_category") val skillCategory: CategoryData,
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}