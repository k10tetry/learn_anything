package com.codewithk10.learnanything.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.codewithk10.learnanything.data.db.converter.AppNotifyConvertor
import com.codewithk10.learnanything.data.db.converter.LocalTimeConverter
import com.codewithk10.learnanything.data.db.dao.SkillDao
import com.codewithk10.learnanything.data.db.entity.Skill

@Database(entities = [Skill::class], version = 1, exportSchema = false)
@TypeConverters(LocalTimeConverter::class, AppNotifyConvertor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun skillDao(): SkillDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "skill_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}