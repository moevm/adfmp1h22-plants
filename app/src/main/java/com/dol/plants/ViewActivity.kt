package com.dol.plants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.dol.plants.data.PlantMap

class ViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        val name = intent.extras?.getString("name")

        findViewById<TextView>(R.id.plant_name_title).text =
            name ?: "Unrecognized plant"

        val propertiesView = findViewById<ListView>(R.id.properties)
        val properties = PlantMap.plants[name]?.toList()

        if (properties != null)
            propertiesView.adapter = ArrayAdapter(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                properties
            )
    }
}
