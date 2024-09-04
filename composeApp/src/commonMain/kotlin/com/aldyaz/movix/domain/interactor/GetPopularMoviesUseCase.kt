package com.aldyaz.movix.domain.interactor

import com.aldyaz.movix.core.domain.ResultState
import com.aldyaz.movix.core.domain.UseCase
import com.aldyaz.movix.domain.model.MovieListDomainModel
import com.aldyaz.movix.domain.repository.MovieRepository

class GetPopularMoviesUseCase(
    private val movieRepository: MovieRepository
) : UseCase<Unit, MovieListDomainModel>() {

    override suspend fun execute(param: Unit): ResultState<MovieListDomainModel> {
        return movieRepository.getPopularMovies()
    }
}