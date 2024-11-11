package com.aldyaz.movix.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
    val xxSmall: Dp = 0.dp,
    val xSmall1: Dp = 0.dp,
    val xSmall2: Dp = 0.dp,
    val small1: Dp = 0.dp,
    val small2: Dp = 0.dp,
    val regular: Dp = 0.dp,
    val medium1: Dp = 0.dp,
    val medium2: Dp = 0.dp,
    val medium3: Dp = 0.dp,
    val medium4: Dp = 0.dp,
    val medium5: Dp = 0.dp,
    val large1: Dp = 0.dp,
    val large2: Dp = 0.dp
)

val CompactSmallDimens = Dimens()

val CompactMediumDimens = Dimens()

val CompactDimens = Dimens(
    xxSmall = 2.dp,
    xSmall1 = 4.dp,
    xSmall2 = 6.dp,
    small1 = 8.dp,
    small2 = 10.dp,
    regular = 12.dp,
    medium1 = 14.dp,
    medium2 = 16.dp,
    medium3 = 18.dp,
    medium4 = 20.dp,
    medium5 = 22.dp,
    large1 = 24.dp,
    large2 = 26.dp
)

val MediumDimens = Dimens()

val ExpandedDimens = Dimens()

val LocalAppDimens = compositionLocalOf {
    CompactDimens
}
