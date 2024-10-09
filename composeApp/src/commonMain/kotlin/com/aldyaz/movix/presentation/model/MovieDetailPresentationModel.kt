package com.aldyaz.movix.presentation.model

data class MovieDetailPresentationModel(
    val id: Long = 0L,
    val title: String = "",
    val overview: String = "",
    val posterPath: String = "",
    val backdropPath: String = "",
    val releaseDate: String = "",
    val genres: List<String> = emptyList(),
    val rating: Double = 0.0,
    val ratingStar: Float = 0f,
    val duration: Int = 0,
    val languages: List<String> = emptyList(),
    val originalLanguage: String = "",
    val budget: Int = 0,
    val revenue: Int = 0,
    val status: String = ""
)