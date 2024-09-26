package com.aldyaz.movix.utils

object MovieImageApi {

    private const val IMAGE_URL = "https://image.tmdb.org/t/p/"

    fun imageW500Url(path: String) = obtainImageUrl(path, MovieImageSize.W500)

    fun imageW780Url(path: String) = obtainImageUrl(path, MovieImageSize.W780)

    fun imageW342Url(path: String) = obtainImageUrl(path, MovieImageSize.W342)

    private fun obtainImageUrl(
        path: String,
        size: MovieImageSize
    ): String = "$IMAGE_URL$size$path"
}

enum class MovieImageSize(private val size: String) {
    W500("w500"),
    W780("w780"),
    W342("w342"),
    ORIGINAL("original");

    override fun toString(): String = size
}
