package com.dol.plants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class ViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        findViewById<TextView>(R.id.plant_name_title).text =
            intent.extras?.getString("name") ?: "Unrecognized plant"
    }
}
