package com.dol.plants

import android.content.Intent
import android.graphics.Color.WHITE
import android.graphics.Color as AColor
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
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

        val whiteButton = findViewById<Button>(R.id.white_color)
        val greenButton = findViewById<Button>(R.id.green_color)
        val deepGreenButton = findViewById<Button>(R.id.deep_green_color)

        val rgbIndicator = findViewById<View>(R.id.rgb)
        rgbIndicator.setBackgroundColor(AColor.rgb(red.progress, green.progress, blue.progress))

        val changeRgbIndicator = object: OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, start: Int, end: Boolean) {
                rgbIndicator.setBackgroundColor(AColor.rgb(red.progress, green.progress, blue.progress))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                rgbIndicator.setBackgroundColor(AColor.rgb(red.progress, green.progress, blue.progress))
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                rgbIndicator.setBackgroundColor(AColor.rgb(red.progress, green.progress, blue.progress))
            }
        }
        red.setOnSeekBarChangeListener(changeRgbIndicator)
        green.setOnSeekBarChangeListener(changeRgbIndicator)
        blue.setOnSeekBarChangeListener(changeRgbIndicator)
        whiteButton.setBackgroundColor(WHITE)
        whiteButton.setOnClickListener {
            red.progress = 255
            green.progress = 255
            blue.progress = 255
        }

        greenButton.setBackgroundColor(AColor.rgb(0, 230, 0))
        greenButton.setOnClickListener {
            red.progress = 0
            green.progress = 230
            blue.progress = 0
        }

        deepGreenButton.setBackgroundColor(AColor.rgb(0, 255, 0))
        deepGreenButton.setOnClickListener {
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