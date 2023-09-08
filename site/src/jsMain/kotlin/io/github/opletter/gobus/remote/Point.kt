package io.github.opletter.gobus.remote


import kotlinx.serialization.Serializable

@Serializable
data class Point(
    val lat: String,
    val lng: String,
)