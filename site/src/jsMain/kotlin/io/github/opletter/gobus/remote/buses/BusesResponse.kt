package io.github.opletter.gobus.remote.buses


import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray

@Serializable
data class BusesResponse(
    val buses: Map<String, List<BusInfo>>,
    val excludedRoutes: JsonArray, // TODO
    val time: Map<String, String>, // key is deviceId???
)