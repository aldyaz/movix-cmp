package com.aldyaz.movix.presentation.intent

sealed class MainHomeTabViewIntent {

    data object NowPlayingRefresh : MainHomeTabViewIntent()

    data object PopularRefresh : MainHomeTabViewIntent()

    data object TopRatedRefresh : MainHomeTabViewIntent()

}