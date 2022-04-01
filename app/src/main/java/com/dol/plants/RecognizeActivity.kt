package com.dol.plants

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.dol.plants.widget.ProgressSeekBar

import com.dol.plants.data.PlantMap.plantList
import com.dol.plants.data.PlantMap.Color


class RecognizeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recognize)

        val red = findViewById<ProgressSeekBar>(R.id.red)
        val green = findViewById<ProgressSeekBar>(R.id.green)
        val blue = findViewById<ProgressSeekBar>(R.id.blue)

        val height = findViewById<ProgressSeekBar>(R.id.height)
        val width = findViewById<ProgressSeekBar>(R.id.width)
        val length = findViewById<ProgressSeekBar>(R.id.length)

        findViewById<Button>(R.id.white_color).setOnClickListener {
            red.progress = 255
            green.progress = 255
            blue.progress = 255
        }

        findViewById<Button>(R.id.green_color).setOnClickListener {
            red.progress = 0
            green.progress = 230
            blue.progress = 0
        }

        findViewById<Button>(R.id.deep_green_color).setOnClickListener {
            red.progress = 0
            green.progress = 255
            blue.progress = 0
        }

        findViewById<Button>(R.id.recognizeAction).setOnClickListener {
            val intent = Intent(this, CatalogueActivity::class.java)
            plantList.filterCrownWidth = width.progress.toUInt() * 100u
            plantList.filterHeight = height.progress.toUInt() * 100u
            plantList.filterLeafLength = length.progress.toUInt()
            plantList.filterColor = Color(
                red.progress.toUByte(),
                green.progress.toUByte(),
                blue.progress.toUByte()
            )
            startActivity(intent)
        }
    }
}