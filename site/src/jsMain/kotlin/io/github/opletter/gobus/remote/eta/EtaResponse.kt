package io.github.opletter.gobus.remote.eta


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class EtaResponse(
    @SerialName("ETAs")
    val etas: Map<String, List<EtaInfo>>,
    val mt: Double,
    val originalRouteIds: List<String>,
    val time: JsonElement, // TODO
    val fromCache8: Int? = null,
)