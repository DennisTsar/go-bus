package io.github.opletter.gobus.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.layout.Divider
import io.github.opletter.gobus.CampusVM
import io.github.opletter.gobus.components.CampusStopsDisplay
import io.github.opletter.gobus.components.ScrollableRow
import io.github.opletter.gobus.remote.PassioApi
import io.github.opletter.gobus.remote.Stop
import io.github.opletter.gobus.remote.eta.EtaInfo
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.Text
import kotlin.time.Duration.Companion.seconds

//https://codepen.io/DivyaPatel/pen/JjjGBKZ

@Page
@Composable
fun HomePage() {
    val campusVM = remember { CampusVM() }
    var allStopsByCampus by remember { mutableStateOf(emptyMap<Campus?, List<Stop>>()) }
    var predictionsToDisplay by remember { mutableStateOf(emptyMap<String, List<EtaInfo>>()) }

    val activeStopsByCampus by remember {
        derivedStateOf {
            allStopsByCampus.filterKeys { campusVM.campuses[it] == true }
        }
    }

    Column(
        Modifier
            .fillMaxWidth()
            .maxWidth(100.percent)
            .padding(leftRight = 0.5.cssRem)
    ) {
        ScrollableRow {
            Campus.values().forEach { campus ->
                Button({ campusVM.toggle(campus) }) { Text(campus.name) }
            }
        }

        activeStopsByCampus.forEach { (campus, stops) ->
            if (campus == null) return@forEach

            CampusStopsDisplay(campus.name, stops, predictionsToDisplay)

            Divider(Modifier.backgroundColor(Colors.Red))
        }
    }

    LaunchedEffect(Unit) {
//        val routesRequest = async { PassioApi.getRoutes() }
        val stopsRequest = async { PassioApi.getStops() }
//        val busesRequest = async { PassioApi.getBuses() }

        val stops = stopsRequest.await()

        allStopsByCampus = stops.stops.values
            .groupBy { stopsByCampus[it.stopId] }
            .filterKeys { it != null }

        val validStops = allStopsByCampus.flatMap { it.value.map { it.stopId } }

        val predictions = PassioApi.getPredictions(validStops)

        predictionsToDisplay = predictions.etas
    }
    LaunchedEffect(activeStopsByCampus) {
        if (activeStopsByCampus.isEmpty()) return@LaunchedEffect
        val validStops = activeStopsByCampus.flatMap { it.value.map { it.stopId } }
        while (true) {
            val predictions = PassioApi.getPredictions(validStops)
            predictionsToDisplay = predictions.etas
            delay(30.seconds) // TODO
        }
    }
}

enum class Campus {
    COLLEGE_AVE,
    BUSCH,
    LIVINGSTON,
    DOWNTOWN_NEW_BRUNSWICK,
    COOK_DOUGLASS
}

val campusStops = mapOf(
    Campus.COLLEGE_AVE to listOf("10038", "27767", "12913", "10035"),
    Campus.BUSCH to listOf("10060", "10034", "21050", "10041", "10052", "10089", "10039"),
    Campus.LIVINGSTON to listOf("10029", "10065", "10071", "10096"),
    Campus.DOWNTOWN_NEW_BRUNSWICK to listOf("62662", "10075"),
    Campus.COOK_DOUGLASS to listOf("10037", "10059", "10042", "10026", "10061", "10036")
)

val stopsByCampus = campusStops.flatMap { (campus, routes) ->
    routes.map { it to campus }
}.toMap()
