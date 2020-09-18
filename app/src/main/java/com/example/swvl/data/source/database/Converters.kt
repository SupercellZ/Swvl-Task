package com.example.swvl.data.source.database

import androidx.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun StringCollectionToString(list: List<String>): String {
        return list.joinToString(separator = ",")
    }

    @TypeConverter
    fun StringToStringCollection(rawString: String): List<String> {
        if (rawString.isEmpty())
            return ArrayList()
        return rawString.split(",").map { it.trim() }
    }
}