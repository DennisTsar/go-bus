package io.github.opletter.gobus.remote


import kotlinx.serialization.Serializable

@Serializable
data class Route(
    val archive: String,
    val color: String,
    val distance: Int,
    val fullname: String,
    val goPrefixRouteName: String,
    val goShowSchedule: Int,
    val id: String,
    val latitude: String,
    val longitude: String,
    val mapApp: String,
    val myid: String,
    val name: String,
    val nameOrig: String,
    val outdated: String,
    val serviceTime: String? = null,
    val serviceTimeShort: String? = null,
    val shortName: String,
    val timezone: String,
    val userId: String,
)