package com.aldyaz.movix.domain.interactor

import com.aldyaz.movix.core.domain.ResultState
import com.aldyaz.movix.core.domain.UseCase
import com.aldyaz.movix.domain.model.MovieDomainModel
import com.aldyaz.movix.domain.repository.MovieRepository

class GetMovieDetailUseCase(
    private val movieRepository: MovieRepository
) : UseCase<Long, MovieDomainModel>() {

    override suspend fun execute(param: Long): ResultState<MovieDomainModel> {
        return movieRepository.getMovieDetail(param)
    }
}