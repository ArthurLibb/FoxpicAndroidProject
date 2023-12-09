package com.example.app.utill

import androidx.room.TypeConverter
import java.util.Date

class FoxPicParser {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}