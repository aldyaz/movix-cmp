package com.aldyaz.movix.domain.mapper

import com.aldyaz.movix.domain.model.MovieCastDomainModel
import com.aldyaz.movix.source.remote.model.MovieCastDto

class MovieCastToDomainMapper : (MovieCastDto) -> MovieCastDomainModel {

    override fun invoke(p1: MovieCastDto): MovieCastDomainModel {
        return MovieCastDomainModel(
            adult = p1.adult ?: false,
            gender = p1.gender ?: 0,
            id = p1.id ?: 0,
            knownForDepartment = p1.knownForDepartment.orEmpty(),
            name = p1.name.orEmpty(),
            originalName = p1.originalName.orEmpty(),
            popularity = p1.popularity ?: 0.0,
            profilePath = p1.profilePath.orEmpty(),
            castId = p1.castId ?: 0,
            character = p1.character.orEmpty(),
            creditId = p1.creditId.orEmpty(),
            order = p1.order ?: 0
        )
    }
}