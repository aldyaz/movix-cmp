package com.aldyaz.movix.data.repository

import com.aldyaz.movix.core.domain.ResultState
import com.aldyaz.movix.core.network.HttpResult
import com.aldyaz.movix.data.cloud.TvShowCloudDataSource
import com.aldyaz.movix.domain.mapper.HttpExceptionToDomainMapper
import com.aldyaz.movix.domain.mapper.TvShowListToDomainMapper
import com.aldyaz.movix.domain.model.TvShowListDomainModel
import com.aldyaz.movix.domain.repository.TvShowRepository

class TvShowRepositoryImpl(
    private val tvShowCloudDataSource: TvShowCloudDataSource,
    private val tvShowListToDomainMapper: TvShowListToDomainMapper,
    private val httpExceptionToDomainMapper: HttpExceptionToDomainMapper
) : TvShowRepository {

    override suspend fun getAiringTodayTvShows(): ResultState<TvShowListDomainModel> {
        return when (val result = tvShowCloudDataSource.getAiringTodayTvShows()) {
            is HttpResult.Success -> ResultState.Success(
                tvShowListToDomainMapper(result.data)
            )

            is HttpResult.Error -> ResultState.Error(
                httpExceptionToDomainMapper(result.exception)
            )
        }
    }

    override suspend fun getOnTheAirTvShows(): ResultState<TvShowListDomainModel> {
        return when (val result = tvShowCloudDataSource.getOnTheAirTvShows()) {
            is HttpResult.Success -> ResultState.Success(
                tvShowListToDomainMapper(result.data)
            )

            is HttpResult.Error -> ResultState.Error(
                httpExceptionToDomainMapper(result.exception)
            )
        }
    }
}