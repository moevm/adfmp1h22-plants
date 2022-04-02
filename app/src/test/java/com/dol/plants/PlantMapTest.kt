package com.dol.plants

import com.dol.plants.data.PlantMap.HistoryWrapper
import com.dol.plants.data.PlantMap.ListWrapper
import junit.framework.TestCase

class PlantMapTest : TestCase() {

    private val list = listOf("Cactus", "Big pretty flower", "Ficus")

    fun correctFilterText() {
        val wrapper = ListWrapper(list)
        wrapper.filterText = "C"

        assertEquals(wrapper.get(), listOf("Cactus", "Ficus"))
    }

    fun correctCreatingHistory() {
        val visited = list.toMutableList()
        visited.add("Cactus")
        val history = HistoryWrapper(visited)

        assertEquals(history.get(), listOf("Cactus", "Ficus", "Big pretty flower", "Cactus"))
    }

    fun correctFilterHistory() {
        val visited = list.toMutableList()
        visited.add("Cactus")
        val history = HistoryWrapper(visited)
        history.sorted = true

        assertEquals(history.get(), listOf("Big pretty flower", "Cactus", "Ficus"))
    }
}