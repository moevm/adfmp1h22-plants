package com.dol.plants

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged

import com.dol.plants.data.PlantMap.ListWrapper

abstract class ListActivity : AppCompatActivity() {

    abstract val listWrapper: ListWrapper

    abstract val layout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        val plantView = findViewById<ListView>(R.id.plant_list)
        plantView.adapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            listWrapper.get()
        )

        findViewById<EditText>(R.id.plantName).doOnTextChanged { text, _, _, _ ->
            listWrapper.filterText = text.toString()
            plantView.adapter = ArrayAdapter(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                listWrapper.get()
            )
        }

        findViewById<Button>(R.id.sort).setOnClickListener {
            listWrapper.sorted = !listWrapper.sorted

            if (listWrapper.sorted)
                it.setBackgroundColor(Color.RED)
            else
                it.setBackgroundColor(Color.rgb(98,0,238))

            plantView.adapter = ArrayAdapter(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                listWrapper.get()
            )
        }

        plantView.setOnItemClickListener { _, _, i, _ ->
            val intent = Intent(this, ViewActivity::class.java)
            val bundle = Bundle()
            val name = listWrapper.get()[i]
            bundle.putString("name", name)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    override fun onResume() {
        findViewById<ListView>(R.id.plant_list).adapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            listWrapper.get()
        )
        super.onResume()
    }

    override fun onBackPressed() {
        listWrapper.reset()
        super.onBackPressed()
    }
}