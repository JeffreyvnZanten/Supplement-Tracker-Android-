package com.jefvaza.supplementtracker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Supplement::class], version = 1, exportSchema = false)
abstract class SupplementsDatabase : RoomDatabase() {
    abstract fun supplementDao(): SupplementsDao

    companion object {
        @Volatile
        private var INSTANCE: SupplementsDatabase? = null

        fun getDatabase(context: Context): SupplementsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SupplementsDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}