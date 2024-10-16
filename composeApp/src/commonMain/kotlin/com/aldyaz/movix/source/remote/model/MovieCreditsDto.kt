package com.aldyaz.movix.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCreditsDto(
    @SerialName("id") val id: Int? = null,
    @SerialName("cast") val cast: List<MovieCastDto>? = null
)

@Serializable
data class MovieCastDto(
    @SerialName("adult") val adult: Boolean? = null,
    @SerialName("gender") val gender: Int? = null,
    @SerialName("id") val id: Int? = null,
    @SerialName("known_for_department") val knownForDepartment: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("original_name") val originalName: String? = null,
    @SerialName("popularity") val popularity: Double? = null,
    @SerialName("profile_path") val profilePath: String? = null,
    @SerialName("cast_id") val castId: Int? = null,
    @SerialName("character") val character: String? = null,
    @SerialName("credit_id") val creditId: String? = null,
    @SerialName("order") val order: Int? = null
)