package com.aldyaz.movix.domain.mapper

import com.aldyaz.movix.domain.model.MovieListDomainModel
import com.aldyaz.movix.source.remote.model.MoviesDto

class MovieListToDomainMapper(
    private val movieToDomainMapper: MovieToDomainMapper
) : (MoviesDto) -> MovieListDomainModel {

    override fun invoke(p1: MoviesDto): MovieListDomainModel {
        return MovieListDomainModel(
            page = p1.page ?: 0,
            movies = p1.results?.map(movieToDomainMapper).orEmpty()
        )
    }
}
