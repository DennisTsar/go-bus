package io.github.opletter.gobus.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.RowScope
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.overflow

@Composable
fun ScrollableRow(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit) {
    Box(Modifier.overflow { x(Overflow.Auto) }) {
        Row(modifier, content = content)
    }
}