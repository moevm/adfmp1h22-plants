package com.dol.plants

import com.dol.plants.data.PlantMap

class HistoryActivity : ListActivity() {

    override val layout: Int = R.layout.activity_history

    override val listWrapper = PlantMap.historyList
}