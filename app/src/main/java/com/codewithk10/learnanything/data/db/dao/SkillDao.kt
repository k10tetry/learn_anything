package com.codewithk10.learnanything.data.db.dao

import androidx.room.*
import com.codewithk10.learnanything.data.db.entity.Skill

@Dao
interface SkillDao {

    @Query("SELECT * FROM skill")
    fun getAllSkills(): List<Skill>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addSkill(skill: Skill)

    @Delete
    fun deleteSkill(skill: Skill)

}