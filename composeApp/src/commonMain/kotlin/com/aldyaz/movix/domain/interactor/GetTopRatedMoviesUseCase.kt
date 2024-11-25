package com.aldyaz.movix.domain.interactor

import com.aldyaz.movix.core.domain.FlowUseCase
import com.aldyaz.movix.core.domain.ResultState
import com.aldyaz.movix.domain.model.MovieListDomainModel
import com.aldyaz.movix.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTopRatedMoviesUseCase(
    private val movieRepository: MovieRepository
) : FlowUseCase<Unit, MovieListDomainModel>() {

    override fun execute(param: Unit): Flow<MovieListDomainModel> = flow {
        when (val result = movieRepository.getTopRatedMovies()) {
            is ResultState.Success -> {
                emit(result.data)
            }

            is ResultState.Error -> {
                throw result.exception
            }
        }
    }
}