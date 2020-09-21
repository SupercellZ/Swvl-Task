package com.example.swvl.data.source.database

import androidx.room.TypeConverter
import java.util.*

/**
 * Used for converting complex data types to DB compatible ones & vice versa
 */
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