package com.dol.plants.data

object PlantMap {

    class Color(
        val red: UByte = 0u,
        val green: UByte = 0u,
        val blue: UByte = 0u
    ) {
        override fun toString(): String {
            return "rgb(${red}, ${green}, ${blue})"
        }
    }

    class PlantProps(
        val width: UIntRange,
        val leafLength: UIntRange,
        val leafColor: Color,
        val height: UIntRange
    ) {

        fun toList(): List<String> {
            return listOf(
                "Width: ${rangeToString(width)}",
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
            width = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 240u),
            height = 300u .. 370u
        ),
        "Fiddle leaf ficus" to PlantProps(
            width = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 230u),
            height = 300u .. 370u
        ),
        "Giant bird of paradise" to PlantProps(
            width = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 220u),
            height = 300u .. 370u
        ),
        "Dragon tree" to PlantProps(
            width = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 210u),
            height = 300u .. 370u
        ),
        "ZZ Plant" to PlantProps(
            width = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 210u),
            height = 300u .. 370u
        ),
        "Ponytail palm" to PlantProps(
            width = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 210u),
            height = 300u .. 370u
        ),
        "Calathea makoyana" to PlantProps(
            width = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 220u),
            height = 300u .. 370u
        ),
        "Sansevieria (snake plant)" to PlantProps(
            width = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 230u),
            height = 300u .. 370u
        ),
        "Aloe vera" to PlantProps(
            width = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 240u),
            height = 300u .. 370u
        ),
        "Pothos" to PlantProps(
            width = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 220u),
            height = 300u .. 370u
        ),
        "Monstera deliciosa" to PlantProps(
            width = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 230u),
            height = 300u .. 370u
        ),
        "Cactus" to PlantProps(
            width = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 240u),
            height = 300u .. 370u
        ),
        "Phalaenopsis white" to PlantProps(
            width = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(red = 255u, green = 255u, blue = 255u),
            height = 300u .. 370u
        ),
        "Succulents" to PlantProps(
            width = 180u .. 240u,
            leafLength = 5u .. 30u,
            leafColor = Color(green = 210u),
            height = 300u .. 370u
        )
    )


    class ListWrapper(private val plantList: List<String>) {

        var filterText: String = ""

        var sorted: Boolean = false

        fun get(): List<String> {
            val filteredPlantList = plantList.filter { plantName ->
                plantName.contains(other = filterText, ignoreCase = true)
            }
            return if (sorted) filteredPlantList.sorted() else filteredPlantList
        }

        fun reset() {
            filterText = ""
            sorted = false
        }
    }
    val plantList = ListWrapper(plants.keys.toList())

}