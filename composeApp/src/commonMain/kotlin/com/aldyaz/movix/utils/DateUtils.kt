package com.aldyaz.movix.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames

object DateUtils {

    /** Example date: Jun 20, 2024 **/
    fun format(dateString: String): String {
        val parseFormat = LocalDate.Format {
            year()
            chars("-")
            monthNumber()
            chars("-")
            dayOfMonth()
        }
        val date = LocalDate.parse(
            input = dateString,
            format = parseFormat
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

}