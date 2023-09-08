package io.github.opletter.gobus.remote

import io.github.opletter.gobus.remote.buses.BusesResponse
import io.github.opletter.gobus.remote.eta.EtaResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object PassioApi {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json, ContentType.Text.Html)
        }
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = "passiogo.com"
            }
        }
    }

    private val deviceId = "1268"

    val routesForm = "{\"systemSelected0\":\"$deviceId\",\"amount\":1}"
    val stopsForm = "{\"s0\":\"$deviceId\",\"sA\":1}"
    val busesForm = "{\"s0\":\"$deviceId\",\"sA\":1}"

    suspend fun getRoutes(): List<Route> {
        return client.post("mapGetData.php") {
            parameter("getRoutes", 1)
            parameter("deviceId", deviceId)
            parameter("wTransloc", 1)
            setBody(FormDataContent(Parameters.build { append("json", routesForm) }))
        }.body()
    }

    suspend fun getStops(): StopsResponse {
        return client.post("mapGetData.php") {
            parameter("getStops", 2)
            parameter("deviceId", deviceId)
            parameter("withOutdated", 1)
            parameter("wBounds", 1)
            parameter("showBusInOos", 0)
            parameter("lat", "undefined")
            parameter("lng", "undefined")
            parameter("wTransloc", 1)
            setBody(FormDataContent(Parameters.build { append("json", stopsForm) }))
        }.body()
    }

    suspend fun getBuses(): BusesResponse {
        return client.post("mapGetData.php") {
            parameter("getBuses", 1)
            parameter("deviceId", deviceId)
            parameter("wTransloc", 1)
            setBody(FormDataContent(Parameters.build { append("json", busesForm) }))
        }.body()
    }

    suspend fun getPredictions(stopIds: Collection<String>): EtaResponse {
        return client.get("mapGetData.php") {
            parameter("eta", 3)
            parameter("deviceId", "20331424")
            parameter("stopIds", stopIds.joinToString(","))
        }.body()
    }
}