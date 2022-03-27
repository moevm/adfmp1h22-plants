package com.dol.plants.data

object PlantMap {

    class PlantProps(
        val leafWidth: BroadOrNarrowLeaves = BroadOrNarrowLeaves.NARROW_LEAVES,
        val leafLength: LongOrShortLeaves = LongOrShortLeaves.SHORT_LEAVES,
        val leafColor: ColorLeaf = ColorLeaf.GREEN,
        val plantSize: BigOrSmallPlant = BigOrSmallPlant.SMALL_PLANT,
    ) {
        enum class BroadOrNarrowLeaves {
            BROAD_LEAVES, NARROW_LEAVES
        }

        enum class LongOrShortLeaves {
            LONG_LEAVES, SHORT_LEAVES
        }

        enum class BigOrSmallPlant {
            BIG_PLANT, SMALL_PLANT, MEDIUM_PLANT
        }

        enum class ColorLeaf {
            WHITE,
            GREEN,
            MULTI_COLORED
        }
    }

    val plants = mapOf(
        "Majesty palm" to PlantProps(
            leafWidth = PlantProps.BroadOrNarrowLeaves.NARROW_LEAVES,
            leafLength = PlantProps.LongOrShortLeaves.LONG_LEAVES,
            leafColor = PlantProps.ColorLeaf.GREEN,
            plantSize = PlantProps.BigOrSmallPlant.MEDIUM_PLANT
        ),
        "Fiddle leaf ficus" to PlantProps(
            leafWidth = PlantProps.BroadOrNarrowLeaves.NARROW_LEAVES,
            leafLength = PlantProps.LongOrShortLeaves.LONG_LEAVES,
            leafColor = PlantProps.ColorLeaf.GREEN,
            plantSize = PlantProps.BigOrSmallPlant.MEDIUM_PLANT
        ),
        "Giant bird of paradise" to PlantProps(
            leafWidth = PlantProps.BroadOrNarrowLeaves.NARROW_LEAVES,
            leafLength = PlantProps.LongOrShortLeaves.LONG_LEAVES,
            leafColor = PlantProps.ColorLeaf.GREEN,
            plantSize = PlantProps.BigOrSmallPlant.MEDIUM_PLANT
        ),
        "Dragon tree" to PlantProps(
            leafWidth = PlantProps.BroadOrNarrowLeaves.NARROW_LEAVES,
            leafLength = PlantProps.LongOrShortLeaves.LONG_LEAVES,
            leafColor = PlantProps.ColorLeaf.GREEN,
            plantSize = PlantProps.BigOrSmallPlant.MEDIUM_PLANT
        ),
        "ZZ Plant" to PlantProps(
            leafWidth = PlantProps.BroadOrNarrowLeaves.NARROW_LEAVES,
            leafLength = PlantProps.LongOrShortLeaves.LONG_LEAVES,
            leafColor = PlantProps.ColorLeaf.GREEN,
            plantSize = PlantProps.BigOrSmallPlant.MEDIUM_PLANT
        ),
        "Ponytail palm" to PlantProps(
            leafWidth = PlantProps.BroadOrNarrowLeaves.NARROW_LEAVES,
            leafLength = PlantProps.LongOrShortLeaves.LONG_LEAVES,
            leafColor = PlantProps.ColorLeaf.GREEN,
            plantSize = PlantProps.BigOrSmallPlant.MEDIUM_PLANT
        ),
        "Calathea makoyana" to PlantProps(
            leafWidth = PlantProps.BroadOrNarrowLeaves.NARROW_LEAVES,
            leafLength = PlantProps.LongOrShortLeaves.LONG_LEAVES,
            leafColor = PlantProps.ColorLeaf.GREEN,
            plantSize = PlantProps.BigOrSmallPlant.MEDIUM_PLANT
        ),
        "Sansevieria (snake plant)" to PlantProps(
            leafWidth = PlantProps.BroadOrNarrowLeaves.NARROW_LEAVES,
            leafLength = PlantProps.LongOrShortLeaves.LONG_LEAVES,
            leafColor = PlantProps.ColorLeaf.GREEN,
            plantSize = PlantProps.BigOrSmallPlant.MEDIUM_PLANT
        ),
        "Aloe vera" to PlantProps(
            leafWidth = PlantProps.BroadOrNarrowLeaves.NARROW_LEAVES,
            leafLength = PlantProps.LongOrShortLeaves.LONG_LEAVES,
            leafColor = PlantProps.ColorLeaf.GREEN,
            plantSize = PlantProps.BigOrSmallPlant.MEDIUM_PLANT
        ),
        "Pothos" to PlantProps(
            leafWidth = PlantProps.BroadOrNarrowLeaves.NARROW_LEAVES,
            leafLength = PlantProps.LongOrShortLeaves.LONG_LEAVES,
            leafColor = PlantProps.ColorLeaf.GREEN,
            plantSize = PlantProps.BigOrSmallPlant.MEDIUM_PLANT
        ),
        "Monstera deliciosa" to PlantProps(
            leafWidth = PlantProps.BroadOrNarrowLeaves.NARROW_LEAVES,
            leafLength = PlantProps.LongOrShortLeaves.LONG_LEAVES,
            leafColor = PlantProps.ColorLeaf.GREEN,
            plantSize = PlantProps.BigOrSmallPlant.MEDIUM_PLANT
        ),
        "Cactus" to PlantProps(
            leafWidth = PlantProps.BroadOrNarrowLeaves.NARROW_LEAVES,
            leafLength = PlantProps.LongOrShortLeaves.LONG_LEAVES,
            leafColor = PlantProps.ColorLeaf.GREEN,
            plantSize = PlantProps.BigOrSmallPlant.MEDIUM_PLANT
        ),
        "Phalaenopsis" to PlantProps(
            leafWidth = PlantProps.BroadOrNarrowLeaves.NARROW_LEAVES,
            leafLength = PlantProps.LongOrShortLeaves.LONG_LEAVES,
            leafColor = PlantProps.ColorLeaf.GREEN,
            plantSize = PlantProps.BigOrSmallPlant.MEDIUM_PLANT
        ),
        "Succulents" to PlantProps(
            leafWidth = PlantProps.BroadOrNarrowLeaves.NARROW_LEAVES,
            leafLength = PlantProps.LongOrShortLeaves.LONG_LEAVES,
            leafColor = PlantProps.ColorLeaf.GREEN,
            plantSize = PlantProps.BigOrSmallPlant.MEDIUM_PLANT
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