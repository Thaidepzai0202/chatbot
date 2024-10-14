package com.example.chatbot.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.FrameLayout
import com.example.chatbot.R

class ShawDownButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    @SuppressLint("ResourceAsColor")
    private val shadowPaint = Paint().apply {
        color = Color.WHITE // Màu nền layout
        setShadowLayer(16f, 0f, 16f, R.color.black) // Màu bóng đổ tùy chỉnh
    }
    fun setShadowColor(color: Int) {
        shadowPaint.setShadowLayer(16f, 0f, 16f, color)
        invalidate() // Vẽ lại layout với màu bóng đổ mới
    }

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, shadowPaint) // Bắt buộc để hiển thị bóng đổ
        setPadding(32, 32, 32, 32) // Đảm bảo bóng đổ không bị cắt
        setBackgroundColor(Color.TRANSPARENT) // Background trong suốt để chỉ hiện bóng đổ
    }

    override fun onDraw(canvas: Canvas) {
        // Vẽ bóng đổ trước khi vẽ nội dung layout
        canvas.drawRoundRect(0f, 0f, width.toFloat(), height.toFloat(), 16f, 16f, shadowPaint)
        super.onDraw(canvas)
    }
}