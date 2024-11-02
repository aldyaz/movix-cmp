package com.aldyaz.movix.domain.mapper

import com.aldyaz.movix.domain.model.MovieListDomainModel
import com.aldyaz.movix.source.remote.model.MoviesDto

class MoviesDtoToDomainMapper(
    private val movieToDomainMapper: MovieToDomainMapper
) : (MoviesDto) -> MovieListDomainModel {

    override fun invoke(p1: MoviesDto): MovieListDomainModel {
        val results = p1.results.orEmpty()
        return MovieListDomainModel(
            page = p1.page ?: 0,
            movies = List(results.size) {
                movieToDomainMapper(results[it])
            }
        )
    }
}
