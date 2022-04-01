package com.dol.plants

import com.dol.plants.data.PlantMap.HistoryWrapper
import com.dol.plants.data.PlantMap.ListWrapper
import org.junit.Test

import org.junit.Assert.*

class PlantMapTest {

    private val list = listOf("Cactus", "Big pretty flower", "Ficus")

    @Test
    fun correctFilterText() {
        val wrapper = ListWrapper(list)
        wrapper.filterText = "C"

        assertEquals(wrapper.get(), listOf("Cactus", "Ficus"))
    }

    @Test
    fun correctCreatingHistory() {
        val visited = list.toMutableList()
        visited.add("Cactus")
        val history = HistoryWrapper(visited)

        assertEquals(history.get(), listOf("Cactus", "Ficus", "Big pretty flower", "Cactus"))
    }

    @Test
    fun correctFilterHistory() {
        val visited = list.toMutableList()
        visited.add("Cactus")
        val history = HistoryWrapper(visited)
        history.sorted = true

        assertEquals(history.get(), listOf("Big pretty flower", "Cactus", "Ficus"))
    }
}