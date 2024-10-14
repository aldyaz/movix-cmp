package com.aldyaz.movix.data.cloud

import com.aldyaz.movix.core.network.HttpResult
import com.aldyaz.movix.data.extension.parseHttp
import com.aldyaz.movix.source.remote.TmdbRemoteService
import com.aldyaz.movix.source.remote.model.TvShowsDto

class TvShowCloudDataSourceImpl(
    private val tmdbRemoteService: TmdbRemoteService
) : TvShowCloudDataSource {

    override suspend fun getAiringTodayTvShows(): HttpResult<TvShowsDto> {
        return parseHttp {
            tmdbRemoteService.getAiringTodayTvShows()
        }
    }

    override suspend fun getOnTheAirTvShows(): HttpResult<TvShowsDto> {
        return parseHttp {
            tmdbRemoteService.getOnTheAirTvShows()
        }
    }
}