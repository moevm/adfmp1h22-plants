package com.dol.plants

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.dol.plants.data.PlantMap.plantList

class CatalogueActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogue)
        val plantView = findViewById<ListView>(R.id.plant_list)
        plantView.adapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            plantList.get()
        )

        findViewById<EditText>(R.id.plantName).doOnTextChanged { text, _, _, _ ->
            plantList.filterText = text.toString()
            plantView.adapter = ArrayAdapter(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                plantList.get()
            )
        }

        findViewById<Button>(R.id.sort).setOnClickListener {
            plantList.sorted = !plantList.sorted
            plantView.adapter = ArrayAdapter(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                plantList.get()
            )
        }

        plantView.setOnItemClickListener { _, _, i, _ ->
            val intent = Intent(that, ViewActivity::class.java)
            val bundle = Bundle()
            bundle.putString("name", plantList.get()[i])
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    private val that = this

    override fun onBackPressed() {
        plantList.reset()
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
