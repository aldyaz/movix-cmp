package com.aldyaz.movix.domain.interactor

import com.aldyaz.movix.core.domain.ResultState
import com.aldyaz.movix.core.domain.UseCase
import com.aldyaz.movix.domain.model.MovieCastDomainModel
import com.aldyaz.movix.domain.repository.MovieRepository

class GetMovieCredits(
    private val movieRepository: MovieRepository
) : UseCase<Long, List<MovieCastDomainModel>>() {

    override suspend fun execute(param: Long): ResultState<List<MovieCastDomainModel>> {
        return movieRepository.getCredits(param)
    }
}