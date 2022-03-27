package com.dol.plants

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.dol.plants.data.PlantMap

class CatalogueActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogue)
        val plantView = findViewById<ListView>(R.id.plant_list)
        plantView.adapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            PlantMap.plantList.get()
        )

        findViewById<EditText>(R.id.plantName).doOnTextChanged { text, _, _, _ ->
            PlantMap.plantList.filterText = text.toString()
            plantView.adapter = ArrayAdapter(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                PlantMap.plantList.get()
            )
        }

        findViewById<Button>(R.id.sort).setOnClickListener {
            PlantMap.plantList.sorted = !PlantMap.plantList.sorted
            plantView.adapter = ArrayAdapter(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                PlantMap.plantList.get()
            )
        }
    }

    override fun onBackPressed() {
        PlantMap.plantList.reset()
        super.onBackPressed()
    }

    companion object {
        @JvmStatic
        private fun getItems(listView: ListView): List<String> {
            return (0 until listView.count)
                .map { i -> listView.adapter.getItem(i) }
                .map { item -> item.toString() }
        }
    }
}