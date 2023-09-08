package io.github.opletter.gobus.remote.eta


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement

@Serializable
data class EtaInfo(
    val actualRouteBlockId: String,
    val arrived: JsonElement, // TODO unsure
    val bg: String,
    val busId: Int,
    val busLatLng: List<Double>,
    val busName: String,
    val busProjectionError: Int,
    val busProjectionLatlng: JsonElement, //TODO: BusProjectionLatlng or empty array?
    val busVirtual: Int,
    val color: String,
    val created: String,
    val deviceId: Int,
    val distance: JsonElement, // TODO: int or "0mi"?
    val dwell: JsonElement, // TODO unsure
    val error: JsonElement, // TODO unsure
    val eta: String,
    val goLimitEta: Int,
    val goShowSchedule: JsonElement, // TODO string or 0?
    val looping: String,
    @SerialName("OOS")
    val oOS: Int,
    val order: Int,
    val outdated: Int,
    val remove: Boolean,
    val routeBlockId: String,
    val routeGroupId: JsonElement, // TODO unsure
    val routeId: String,
    val routePointPosition: Int,
    val routeStopPosition: Int,
    val scheduleTimes: JsonArray, // TODO
    val secondsSpent: Int,
    val secondsSpentOriginal: Int,
    val serviceTime: String,
    val speed: JsonElement, // TODO double or string?
    val stopId: String,
    val stopRoutePointPosition: Int,
    val stopsAmount: Int,
    val theStop: EtaStop,
    val timezoneOffset: Int,
    val tripId: Int,
)