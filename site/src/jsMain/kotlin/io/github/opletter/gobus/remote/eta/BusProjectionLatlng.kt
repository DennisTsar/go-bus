package io.github.opletter.gobus.remote.eta


import kotlinx.serialization.Serializable

@Serializable
data class BusProjectionLatlng(
    val lat: Double,
    val lng: Double,
)