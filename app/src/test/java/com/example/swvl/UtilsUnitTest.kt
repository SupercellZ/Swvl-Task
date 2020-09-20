package com.example.swvl

import com.example.swvl.data.source.database.Converters
import org.junit.Test

class UtilsUnitTest {

    @Test
    fun stringListConversion() {
        val converters = Converters()

        val originalList = arrayListOf("1", "2", "3", "4")
        val joinedString = converters.StringCollectionToString(originalList)
        val expandedList = converters.StringToStringCollection(joinedString)
        assert(originalList == expandedList)
    }
}