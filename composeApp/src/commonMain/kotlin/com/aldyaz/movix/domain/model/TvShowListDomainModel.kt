package com.aldyaz.movix.domain.model

data class TvShowListDomainModel(
    val page: Int,
    val tvShows: List<TvShowDomainModel>
)