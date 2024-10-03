package com.aldyaz.movix.utils

import kotlinx.datetime.LocalTime
import kotlinx.datetime.format
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char

object TimeUtils {

    fun minutesTotal(millis: Int): Int = millisToMinutes(millis)

    fun formatHourMinutes(millis: Int): String {
        val hours = millisToHours(millis)
        val minutes = millisToMinutes(millis)
        val time = LocalTime(
            hour = hours,
            minute = minutes
        )
        return time.format(
            LocalTime.Format {
                hour(padding = Padding.ZERO)
                char(':')
                minute(padding = Padding.ZERO)
            }
        )
    }

    private fun millisToHours(millis: Int) = millis.div(60)

    private fun millisToMinutes(millis: Int) = millis % 60
}