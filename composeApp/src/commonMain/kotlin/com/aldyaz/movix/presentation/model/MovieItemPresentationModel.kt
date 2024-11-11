package com.aldyaz.movix.presentation.model

data class MovieItemPresentationModel(
    val id: Long = 0L,
    val title: String = "",
    val posterPath: String = "",
    val rating: Double = 0.0,
    val ratingStar: Float = 0f,
    val genres: List<String> = emptyList()
)