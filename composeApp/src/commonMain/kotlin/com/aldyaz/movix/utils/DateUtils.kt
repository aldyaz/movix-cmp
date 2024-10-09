package com.aldyaz.movix.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames

object DateUtils {

    /** Example date: Jun 20, 2024 **/
    fun format(dateString: String): String {
        val date = LocalDate.parse(
            input = dateString,
            format = commonDateFormat()
        )
        return date.format(
            LocalDate.Format {
                monthName(MonthNames.ENGLISH_ABBREVIATED)
                chars(" ")
                dayOfMonth()
                chars(", ")
                year()
            }
        )
    }

    fun getYear(dateString: String): Int {
        val date = LocalDate.parse(
            input = dateString,
            format = commonDateFormat()
        )
        return date.year
    }

    private fun commonDateFormat() = LocalDate.Format {
        year()
        chars("-")
        monthNumber()
        chars("-")
        dayOfMonth()
    }

}