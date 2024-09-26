package com.aldyaz.movix.utils

import kotlin.math.pow
import kotlin.math.roundToInt

fun Double.round(fraction: Int): Double {
    val factor = 10.0.pow(fraction)
    return (this * factor).roundToInt() / factor
}