package com.aldyaz.movix.domain.interactor

import com.aldyaz.movix.core.domain.ResultState
import com.aldyaz.movix.core.domain.UseCase
import com.aldyaz.movix.domain.model.TvShowListDomainModel
import com.aldyaz.movix.domain.repository.TvShowRepository

class GetAiringTodayTvShowsUseCase(
    private val tvShowRepository: TvShowRepository
) : UseCase<Unit, TvShowListDomainModel>() {

    override suspend fun execute(param: Unit): ResultState<TvShowListDomainModel> {
        return tvShowRepository.getAiringTodayTvShows()
    }
}