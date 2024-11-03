package com.aldyaz.movix.source.local.model

data class MovieDbModel(
    val id: Long,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val genres: String,
    val voteAverage: Double,
    val runtime: Int,
    val languages: String,
    val originalLanguage: String,
    val budget: Int,
    val revenue: Int,
    val status: String
)