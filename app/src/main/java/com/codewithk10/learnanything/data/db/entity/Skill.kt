package com.codewithk10.learnanything.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "skill")
data class Skill(
    @ColumnInfo(name = "skill_title") val skillTitle: String,
    @ColumnInfo(name = "skill_note") val skillNote: String?,
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}