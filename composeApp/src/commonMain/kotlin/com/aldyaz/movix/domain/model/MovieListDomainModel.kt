package com.aldyaz.movix.domain.model

data class MovieListDomainModel(
    val page: Int,
    val movies: List<MovieDomainModel>
)