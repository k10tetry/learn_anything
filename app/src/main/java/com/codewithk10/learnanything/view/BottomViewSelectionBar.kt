package com.codewithk10.learnanything.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.animation.LinearInterpolator
import com.codewithk10.learnanything.R
import kotlin.math.min

class BottomViewSelectionBar : View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var barColor = Color.BLACK
    private var barHeight = dpToFloat(4f)
    private var isBarRound = true
    private var barCount = 4
    private var selectedIndex = 0
    private var barMode = BarMode.FIXED
    private var startPoint = 0f
    private var endPoint = 0f
    private var rectF: RectF? = null

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attributes: AttributeSet) : super(context, attributes) {
        initAttributes(attributes)
    }

    private fun initAttributes(attributes: AttributeSet) {
        val typeArray = context.theme.obtainStyledAttributes(
            attributes,
            R.styleable.BottomViewSelectionBar,
            0,
            0
        )

        barColor = typeArray.getColor(R.styleable.BottomViewSelectionBar_barColor, Color.BLACK)
        barHeight =
            typeArray.getDimension(R.styleable.BottomViewSelectionBar_barHeight, dpToFloat(4f))
        isBarRound = typeArray.getBoolean(R.styleable.BottomViewSelectionBar_isBarRound, true)
        barMode = BarMode.values()[typeArray.getInt(R.styleable.BottomViewSelectionBar_barMode, 0)]
        barCount = typeArray.getInt(R.styleable.BottomViewSelectionBar_barCount, 4)
        typeArray.recycle()
    }

    fun setSelectedIndex(index: Int) {
        if (index in 0 until barCount) {
            selectedIndex = index
            invalidate()
        }
    }

    fun animate(selectedIndex: Int) {
        this.selectedIndex = selectedIndex
        calculateBarSize()
        val animator = ObjectAnimator.ofFloat(this, "translationX", startPoint, endPoint)
        animator.duration = 300L
        animator.interpolator = LinearInterpolator()
        animator.start()
        startPoint = endPoint
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        calculateBarSize()
        drawBar(canvas)
    }

    private fun drawBar(canvas: Canvas?) {
        paint.color = barColor
        paint.style = Paint.Style.FILL
        if (isBarRound) {
            val radius = (height / 2).toFloat()
            canvas?.drawRoundRect(rectF!!, radius, radius, paint)
        } else {
            canvas?.drawRect(rectF!!, paint)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(widthMeasureSpec, calculateBarHeight().toInt())
    }

    private fun calculateBarHeight(): Float {
        return min(barHeight, dpToFloat(8f));
    }

    private fun dpToFloat(dips: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dips,
            context.resources.displayMetrics
        )
    }

    private fun calculateBarSize() {
        val dividedWidth = (width / barCount)
        var leftPoint = dividedWidth * selectedIndex
        var rightPoint = dividedWidth * (selectedIndex + 1)

        this.endPoint = leftPoint.toFloat()

        if (barMode == BarMode.FIXED) {
            leftPoint += (dividedWidth / 4)
            rightPoint -= (dividedWidth / 4)
        }
        rectF = RectF(leftPoint.toFloat(), 0f, rightPoint.toFloat(), height.toFloat())
    }

    enum class BarMode {
        FIXED, STRETCH
    }

}