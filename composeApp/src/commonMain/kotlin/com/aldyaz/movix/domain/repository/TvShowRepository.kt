package com.aldyaz.movix.domain.repository

import com.aldyaz.movix.core.domain.ResultState
import com.aldyaz.movix.domain.model.TvShowListDomainModel

interface TvShowRepository {

    suspend fun getAiringTodayTvShows(): ResultState<TvShowListDomainModel>

    suspend fun getOnTheAirTvShows(): ResultState<TvShowListDomainModel>

}