package io.github.opletter.gobus.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.flexShrink
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.silk.components.text.SpanText
import io.github.opletter.gobus.remote.Stop
import io.github.opletter.gobus.remote.eta.EtaInfo
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Text

@Composable
fun CampusStopsDisplay(campus: String, stops: List<Stop>, predictionsToDisplay: Map<String, List<EtaInfo>>) {
    H2 { Text(campus) }
    Column(Modifier.gap(0.25.cssRem)) {
        stops.forEach { stop ->
            SpanText(stop.name)
            predictionsToDisplay[stop.stopId]?.let { BusChips(it) }
        }
    }
}

@Composable
private fun BusChips(data: List<EtaInfo>) {
    ScrollableRow(Modifier.gap(0.5.cssRem)) {
        data.sortedBy { evaluateEta(it.eta) }.forEach { eta ->
            Box(
                Modifier
                    .backgroundColor(Colors.LightBlue)
                    .padding(0.5.cssRem)
                    .flexShrink(0)
            ) {
                Text("${eta.theStop.routeName.substringBefore(' ')}: ${eta.eta}")
            }
        }
    }
}

private fun evaluateEta(eta: String): Int {
    return when {
        eta == "arriving" || eta == "arrived" || eta == "due" -> 0
        eta == "less than 1 min" -> 1
        eta == "--" -> 1000
        "-" in eta -> eta.substringBefore('-').toIntOrNull()
            ?: 100.also { println("Unknown ETA!!!!: $eta") }

        else -> eta.substringBefore(' ').toIntOrNull()
            ?: 100.also { println("Unknown ETA: $eta") }
    }
}