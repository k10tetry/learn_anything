package com.codewithk10.learnanything.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codewithk10.learnanything.ui.skill.data.CategoryData
import com.codewithk10.learnanything.ui.skill.data.TargetData
import com.codewithk10.learnanything.utils.notification.AppNotify

@Entity(tableName = "skill")
data class Skill(
    @ColumnInfo(name = "skill_title") val skillTitle: String,
    @ColumnInfo(name = "skill_note") val skillNote: String,
    @ColumnInfo(name = "skill_notification") val skillNotification: AppNotify,
    @ColumnInfo(name = "skill_target") val skillTarget: TargetData,
    @ColumnInfo(name = "skill_category") val skillCategory: CategoryData,
    @ColumnInfo(name = "skill_create_date") val skillCreateDate: Long,
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}