package io.github.opletter.gobus.remote.buses


import kotlinx.serialization.Serializable

@Serializable
data class BusInfo(
    val bus: String,
    val busId: Int,
    val busName: String,
    val busType: String,
    val calculatedCourse: String,
    val color: String,
    val created: String,
    val createdDebug: String,
    val createdTime: String,
    val deviceId: Int,
    val latitude: String,
    val longitude: String,
    val more: String?,
    val outOfService: Int,
    val outdated: Int,
    val paxLoad: Int,
    val route: String,
    val routeBlockId: String,
    val routeId: String,
    val totalCap: Int,
    val userId: String,
    val snapDistance: Double? = null,
)