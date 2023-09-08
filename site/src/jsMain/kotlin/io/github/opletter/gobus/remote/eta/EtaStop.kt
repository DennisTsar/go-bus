package io.github.opletter.gobus.remote.eta


import kotlinx.serialization.Serializable

@Serializable
data class EtaStop(
    val busId: Int,
    val name: String,
    val position: Int,
    val routeId: String,
    val routeName: String,
    val routeStopId: String,
    val shortName: String,
    val stopId: String,
    val userId: String,
)