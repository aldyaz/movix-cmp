package com.aldyaz.movix.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MoviesDto(
    @SerialName("results")
    val results: List<MovieDto>? = listOf(),
    @SerialName("page")
    val page: Int? = null,
    @SerialName("total_results")
    val totalResults: Int? = null,
    @SerialName("total_pages")
    val totalPages: Int? = null
)

@Serializable
class MovieDto(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("vote_average")
    val voteAverage: Double? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("original_title")
    val originalTitle: String? = null,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("overview")
    val overview: String? = null,
    @SerialName("release_date")
    val releaseDate: String? = null,
    @SerialName("genres")
    val genres: List<GenreDto>? = listOf(),
    @SerialName("popularity")
    val popularity: Double? = null,
    @SerialName("genre_ids")
    val genreIds: List<Int>? = listOf(),
    @SerialName("runtime")
    val runtime: Int? = null
)