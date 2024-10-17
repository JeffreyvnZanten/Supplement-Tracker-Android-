package com.jefvaza.supplementtracker;

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "supplements")
data class Supplement(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val timestamp: Long = System.currentTimeMillis()
)