package io.github.opletter.gobus.remote


import kotlinx.serialization.Serializable

@Serializable
data class Stop(
    val id: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val position: String,
    val radius: Int,
    val routeGroupId: Int,
    val routeId: String,
    val routeName: String,
    val routeShortname: String,
    val stopId: String,
    val userId: String,
)