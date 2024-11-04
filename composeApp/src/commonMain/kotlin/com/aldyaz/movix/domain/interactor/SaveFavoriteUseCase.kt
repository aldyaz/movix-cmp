package com.aldyaz.movix.domain.interactor

import com.aldyaz.movix.core.domain.ResultState
import com.aldyaz.movix.core.domain.UseCase
import com.aldyaz.movix.domain.model.MovieDomainModel
import com.aldyaz.movix.domain.repository.MovieRepository

class SaveFavoriteUseCase(
    private val movieRepository: MovieRepository
) : UseCase<MovieDomainModel, Unit>() {

    override suspend fun execute(param: MovieDomainModel): ResultState<Unit> {
        return movieRepository.saveFavorite(param)
    }
}