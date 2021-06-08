package com.codewithk10.learnanything.data.db.dao

import androidx.room.*
import com.codewithk10.learnanything.data.db.entity.Skill
import com.codewithk10.learnanything.utils.notification.AppNotify
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe

@Dao
interface SkillDao {

    @Query("SELECT * FROM skill")
    fun getAllSkills(): Flowable<List<Skill>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSkill(skill: Skill): Maybe<Unit>

    @Delete
    fun deleteSkill(skill: Skill): Maybe<Unit>

    @Query("SELECT skill_notification FROM skill")
    fun getAllSkillNotifications(): Flowable<List<AppNotify>>

}