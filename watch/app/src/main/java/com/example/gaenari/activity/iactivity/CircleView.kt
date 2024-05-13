package com.example.gaenari.activity.iactivity

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.gaenari.R
import android.view.ViewGroup
import androidx.core.content.ContextCompat

class CircleView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    var isRunning: Boolean = false
    var isActive: Boolean = false
    var text: String = ""

    private val orangeDrawable = ContextCompat.getDrawable(context, R.drawable.btn_orange)
    private val greenDrawable = ContextCompat.getDrawable(context, R.drawable.btn_green)

    fun updateView(isRunning: Boolean, isActive: Boolean) {
        this.isRunning = isRunning
        this.isActive = isActive
        this.text = if (isRunning) "run" else "walk"
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val drawable = if (isRunning) greenDrawable else orangeDrawable
        // 활성화 상태에 따라 크기를 조정합니다.
        val sizeIncrease = if (isActive) 1.3 else 0.7  // 활성화 상태에서는 크기를 25% 늘립니다.
        val drawableWidth = (width * sizeIncrease).toInt()
        val drawableHeight = (height * sizeIncrease).toInt()
        val left = (width - drawableWidth) / 2
        val top = (height - drawableHeight) / 2

        drawable?.setBounds(left, top, left + drawableWidth, top + drawableHeight)
        drawable?.draw(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredSize = (20 * resources.displayMetrics.density).toInt()  // 반지름을 30dp로 증가

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val width = if (widthMode == MeasureSpec.EXACTLY) widthSize else desiredSize
        val height = if (heightMode == MeasureSpec.EXACTLY) heightSize else desiredSize

        setMeasuredDimension(width, height)  // 실제 뷰의 크기를 설정
    }
}
