package com.dol.plants.data

import kotlin.math.abs
import kotlin.math.sqrt

object PlantMap {

    data class Color(
        val red: UByte = 0u,
        val green: UByte = 0u,
        val blue: UByte = 0u
    ) {
        override fun toString(): String {
            return "rgb(${red}, ${green}, ${blue})"
        }

        fun absolute(): Double {
            val dRed = red.toDouble()
            val dGreen = green.toDouble()
            val dBlue = blue.toDouble()
            return sqrt(dRed * dRed + dBlue * dBlue + dGreen * dGreen)
        }
    }

    data class PlantProps(
        val crownWidth: UIntRange,
        val leafLength: UIntRange,
        val leafColor: Color,
        val height: UIntRange
    ) {

        fun toList(): List<String> {
            return listOf(
                "Crown width: ${rangeToString(crownWidth)}",
                "Leaf length: ${rangeToString(leafLength)}",
                "LeafColor: $leafColor",
                "Height: ${rangeToString(height)}"
            )
        }

        companion object PlantProps {
            @JvmStatic
            fun <T : Comparable<T>> rangeToString(range: ClosedRange<T>): String {
                return "from ${range.start} to ${range.endInclusive}"
            }
        }
    }

    val plants = mapOf(
        "Majesty palm" to PlantProps(
            crownWidth = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 240u),
            height = 300u .. 370u
        ),
        "Fiddle leaf ficus" to PlantProps(
            crownWidth = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 230u),
            height = 300u .. 370u
        ),
        "Giant bird of paradise" to PlantProps(
            crownWidth = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 220u),
            height = 300u .. 370u
        ),
        "Dragon tree" to PlantProps(
            crownWidth = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 210u),
            height = 300u .. 370u
        ),
        "ZZ Plant" to PlantProps(
            crownWidth = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 210u),
            height = 300u .. 370u
        ),
        "Ponytail palm" to PlantProps(
            crownWidth = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 210u),
            height = 300u .. 370u
        ),
        "Calathea makoyana" to PlantProps(
            crownWidth = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 220u),
            height = 300u .. 370u
        ),
        "Sansevieria (snake plant)" to PlantProps(
            crownWidth = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 230u),
            height = 300u .. 370u
        ),
        "Aloe vera" to PlantProps(
            crownWidth = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 240u),
            height = 300u .. 370u
        ),
        "Pothos" to PlantProps(
            crownWidth = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 220u),
            height = 300u .. 370u
        ),
        "Monstera deliciosa" to PlantProps(
            crownWidth = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 230u),
            height = 300u .. 370u
        ),
        "Cactus" to PlantProps(
            crownWidth = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 240u),
            height = 300u .. 370u
        ),
        "Phalaenopsis white" to PlantProps(
            crownWidth = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(red = 255u, green = 255u, blue = 255u),
            height = 300u .. 370u
        ),
        "Succulents" to PlantProps(
            crownWidth = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 210u),
            height = 300u .. 370u
        )
    )


    open class ListWrapper(private var plantList: List<String>) {

        var filterText: String = ""

        var filterColor: Color? = null

        var filterLeafLength: UInt? = null

        var filterHeight: UInt? = null

        var filterCrownWidth: UInt? = null

        var sorted: Boolean = false

        fun filterNSortIsNotSet(): Boolean {
            return filterText.isEmpty() && filterColor == null && filterLeafLength == null &&
                    filterHeight == null && filterCrownWidth == null && !sorted
        }

        open fun get(): List<String> {
            val filteredPlantList = plantList
                .asSequence()
                .filter {
                    it.contains(other = filterText, ignoreCase = true)
                }
                .filter {
                    if (filterColor != null) {
                        val color = plants[it]?.leafColor
                        if (color == null) false
                        else abs(color.absolute() - filterColor!!.absolute()) <= 10.0
                    } else true
                }
                .filter {
                    if (filterLeafLength != null) {
                        val leafLength = plants[it]?.leafLength
                        leafLength?.contains(filterLeafLength) ?: false
                    } else true
                }
                .filter {
                    if (filterHeight != null) {
                        val height = plants[it]?.height
                        height?.contains(filterHeight) ?: false
                    } else true
                }
                .filter {
                    if (filterCrownWidth != null) {
                        val crownWidth = plants[it]?.crownWidth
                        crownWidth?.contains(filterCrownWidth) ?: false
                    } else true
                }
                .toList()
            return if (sorted) filteredPlantList.sorted() else filteredPlantList
        }

        fun reset() {
            filterText = ""
            filterColor = null
            filterLeafLength = null
            filterHeight = null
            filterCrownWidth = null
            sorted = false
        }
    }

    class HistoryWrapper(historyList: List<String>): ListWrapper(historyList.asReversed()) {

        override fun get(): List<String> {
            return if (filterNSortIsNotSet())
                super.get()
            else
                super.get().fold(ArrayList()) { res, el ->
                    if (res.contains(el)) {
                        res
                    } else {
                        res.add(el)
                        res
                    }
                }
        }
    }

    val visited = ArrayList<String>()

    val plantList = ListWrapper(plants.keys.toList())

    val historyList = HistoryWrapper(visited)
}