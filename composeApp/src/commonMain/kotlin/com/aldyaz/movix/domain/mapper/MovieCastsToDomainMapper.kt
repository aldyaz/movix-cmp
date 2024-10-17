package com.aldyaz.movix.domain.mapper

import com.aldyaz.movix.domain.model.MovieCastDomainModel
import com.aldyaz.movix.source.remote.model.MovieCreditsDto

class MovieCastsToDomainMapper(
    private val movieCastToDomainMapper: MovieCastToDomainMapper
) : (MovieCreditsDto) -> List<MovieCastDomainModel> {

    override fun invoke(p1: MovieCreditsDto): List<MovieCastDomainModel> {
        val items = p1.cast.orEmpty()
        return List(items.size) {
            movieCastToDomainMapper(items[it])
        }
    }
}