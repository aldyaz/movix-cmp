package com.aldyaz.movix.data.cloud

import com.aldyaz.movix.core.network.HttpResult
import com.aldyaz.movix.source.remote.model.TvShowsDto

interface TvShowCloudDataSource {

    suspend fun getAiringTodayTvShows(): HttpResult<TvShowsDto>

    suspend fun getOnTheAirTvShows(): HttpResult<TvShowsDto>

}