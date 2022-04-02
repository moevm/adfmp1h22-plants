package com.dol.plants.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatSeekBar


class ProgressSeekBar : AppCompatSeekBar {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("DrawAllocation")
    override fun onDraw(c: Canvas) {
        super.onDraw(c)

        if (isPressed) {
            val paint = Paint()
            paint.color = Color.GRAY
            paint.textSize = 20f
            min
            val normalProgress = (progress - min).toFloat() / (max - min)
            c.drawText(
                progress.toString(),
                paddingLeft + (width - paddingLeft - paddingRight) * normalProgress - 15,
                paddingEnd.toFloat() - 2,
                paint
            )
        }
    }
}