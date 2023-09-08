package io.github.opletter.gobus.remote


import kotlinx.serialization.Serializable

@Serializable
data class Center(
    val amount: String,
    val amountYard: String,
    val d: Double,
    val hub: List<Double>,
    val hubHints: List<String>,
    val hubOld: List<String>,
    val latitude: String,
    val latitudeMax: String,
    val latitudeMin: String,
    val longitude: String,
    val longitudeMax: String,
    val longitudeMin: String,
    val t: Double,
)