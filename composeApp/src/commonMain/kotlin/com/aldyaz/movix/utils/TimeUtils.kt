package com.aldyaz.movix.utils

import kotlinx.datetime.LocalTime
import kotlinx.datetime.format
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char

object TimeUtils {

    fun formatHourMinutes(millis: Int): String {
        val hours = millis.div(60)
        val minutes = millis % 60
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
}