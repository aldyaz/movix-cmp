package com.aldyaz.movix.domain.mapper

import com.aldyaz.movix.domain.model.TvShowListDomainModel
import com.aldyaz.movix.source.remote.model.TvShowsDto

class TvShowListToDomainMapper(
    private val tvShowToDomainMapper: TvShowToDomainMapper
) : (TvShowsDto) -> TvShowListDomainModel {

    override fun invoke(p1: TvShowsDto): TvShowListDomainModel {
        val results = p1.results.orEmpty()
        return TvShowListDomainModel(
            page = p1.page ?: 0,
            tvShows = List(results.size) {
                tvShowToDomainMapper(results[it])
            }
        )
    }
}