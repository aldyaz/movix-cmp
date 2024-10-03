package com.aldyaz.movix.presentation.model

data class MovieDetailPresentationModel(
    val id: Long = 0L,
    val title: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val posterPath: String = "",
    val backdropPath: String = "",
    val releaseDate: String = "",
    val genres: List<String> = emptyList(),
    val rating: Double = 0.0,
    val ratingStar: Float = 0f,
    val duration: Int = 0
)