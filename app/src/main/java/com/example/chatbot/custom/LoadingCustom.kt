package com.example.chatbot.custom

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Interpolator
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.core.content.ContextCompat
import com.example.chatbot.R

class LoadingCustom @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val dotRadius = 15f
    private val paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.black)
        style = Paint.Style.FILL
    }

    private val interpolator: OvershootInterpolator = OvershootInterpolator()

    private var rotationAngle = 0f
    private var animationSpeed = 0f
    private var distanceFactor = 1f

    private val icon: Bitmap =
        drawableToBitmap(ContextCompat.getDrawable(context, R.drawable.circle_indicator_active)!!)

    init {
        // Tạo một animation để thay đổi tốc độ và khoảng cách
        post(object : Runnable {
            override fun run() {
                updateRotation()
                invalidate()
                postDelayed(this, 16) // 60 FPS
            }
        })
    }

    private fun updateRotation() {
        rotationAngle += animationSpeed

        // Tăng tốc độ quay và khoảng cách
        if (rotationAngle < 360f) {
            animationSpeed += 0.2f

        } else if (rotationAngle >= 360 && rotationAngle < 720) {
            animationSpeed -= 0.2f

        } else {
            rotationAngle %= 360f
            animationSpeed = 0f
        }
        distanceFactor = interpolator.getInterpolation(0.5f + animationSpeed / 20f)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val distance = 50f * distanceFactor

        // Vẽ 3 dấu chấm
        for (i in 0..2) {
            val angle = Math.toRadians((rotationAngle + i * 120).toDouble())
            val x = (centerX + distance * Math.cos(angle)).toFloat()
            val y = (centerY + distance * Math.sin(angle)).toFloat()
//            canvas.drawBitmap(icon, x, y, paint)
            canvas.save() // Lưu trạng thái hiện tại của canvas

            // Xoay canvas để icon chĩa ra ngoài
//            canvas.rotate((rotationAngle + i * 60 ).toFloat(), x, y)

            // Vẽ icon tại vị trí đã xoay
            canvas.drawBitmap(icon, x - icon.width / 2, y - icon.height / 2, paint)

            canvas.restore()
        }
    }

    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}