package com.dol.plants

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.catalog).setOnClickListener {
            val intent = Intent(this, CatalogueActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.recognize).setOnClickListener {
            val intent = Intent(this, RecognizeActivity::class.java)
            startActivity(intent)
        }
    }
}