package io.github.opletter.gobus

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.github.opletter.gobus.pages.Campus
import kotlinx.browser.localStorage
import org.w3c.dom.get
import org.w3c.dom.set

class CampusVM {
    var campuses by mutableStateOf(emptyMap<Campus, Boolean>())

    init {
        val savedCampuses = localStorage["go-bus:campuses"]?.split(',')?.map { it.toBoolean() }
        campuses = Campus.values().zip(savedCampuses ?: List(Campus.values().size) { true }).toMap()
    }

    fun toggle(campus: Campus) {
        campuses = campuses.toMutableMap().apply {
            this[campus] = !this[campus]!!
        }
        localStorage["go-bus:campuses"] = campuses.values.joinToString(",")
    }
}