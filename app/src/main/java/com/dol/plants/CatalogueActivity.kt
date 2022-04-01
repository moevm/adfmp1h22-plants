package com.dol.plants

import com.dol.plants.data.PlantMap.ListWrapper
import com.dol.plants.data.PlantMap.plantList

class CatalogueActivity : ListActivity() {

    override val layout: Int = R.layout.activity_catalogue

    override val listWrapper: ListWrapper = plantList
}
