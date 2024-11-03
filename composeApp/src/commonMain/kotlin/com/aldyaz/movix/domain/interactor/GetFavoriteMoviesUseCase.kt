package com.aldyaz.movix.domain.interactor

import com.aldyaz.movix.core.domain.FlowUseCase
import com.aldyaz.movix.domain.model.MovieDomainModel
import com.aldyaz.movix.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteMoviesUseCase(
    private val movieRepository: MovieRepository
) : FlowUseCase<Unit, List<MovieDomainModel>>() {

    override fun execute(param: Unit): Flow<List<MovieDomainModel>> {
        return movieRepository.getFavoriteMovies()
    }
}
