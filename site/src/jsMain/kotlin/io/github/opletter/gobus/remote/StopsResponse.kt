package io.github.opletter.gobus.remote


import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement

@Serializable
data class StopsResponse(
    val center: Center,
    val excludedRoutesID: Map<String, JsonElement>, // value can be string or num?
    val groupRoutes: Boolean,
    val routePoints: Map<String, List<List<Point>>>,
    val routeSchedules: Map<String, String>,
    val routeShortNames: Map<String, String?>,
    val routes: Map<String, JsonArray>, // TODO value
    val routesRR: List<String>, // TODO
    val stops: Map<String, Stop>,
    val stopsRR: List<String>, // TODO
)