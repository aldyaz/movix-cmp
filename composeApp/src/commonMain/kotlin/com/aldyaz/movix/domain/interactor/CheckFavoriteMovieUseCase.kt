package com.aldyaz.movix.domain.interactor

import com.aldyaz.movix.core.domain.FlowUseCase
import com.aldyaz.movix.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class CheckFavoriteMovieUseCase(
    private val movieRepository: MovieRepository
) : FlowUseCase<Long, Boolean>() {

    override fun execute(param: Long): Flow<Boolean> {
        return movieRepository.checkFavorite(param)
    }
}