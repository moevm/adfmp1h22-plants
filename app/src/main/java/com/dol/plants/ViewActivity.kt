package com.dol.plants

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.dol.plants.data.PlantMap
import java.io.File
import java.io.FileOutputStream
import java.util.*


class ViewActivity : AppCompatActivity() {
    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        val name = intent.extras?.getString("name")

        if (name != null) {
            PlantMap.visited.add(name)
        }

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

        findViewById<Button>(R.id.share).setOnClickListener {
            val file = takeScreenshot()
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "image/jpeg"
            val uri = file?.let {
                FileProvider.getUriForFile(
                    this,
                    applicationContext.packageName + ".provider",
                    it
                )
            }
            uri?.let {
                intent.putExtra(Intent.EXTRA_STREAM, it)
            }
            val chooser = Intent.createChooser(intent,"Share to:")
            grantUriPermission(
                chooser.resolveActivityInfo(
                    packageManager,
                    PackageManager.MATCH_DEFAULT_ONLY
                ).packageName,
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            startActivity(chooser)
        }
    }

    private fun takeScreenshot(): File? {
        return try {
            val path = getUniquePicturePath()
            val bitmap = Bitmap.createBitmap(getBitmapFromView(window.decorView.rootView))
            val imageFile = File(path)
            val outputStream = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
            imageFile
        } catch (e: Throwable) {
            e.printStackTrace()
            null
        }
    }

    private fun getUniquePicturePath(): String {
        val now = Date()
        val path = Environment
            .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            .toString() + "/" + now + ".jpg"
        return path.replace(":", ".")
    }

    private fun getBitmapFromView(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }
}
