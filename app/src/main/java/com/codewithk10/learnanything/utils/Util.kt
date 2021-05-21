package com.codewithk10.learnanything.utils

import android.content.Context
import android.util.TypedValue

object Util {

    fun dpToPx(context: Context, dp: Float): Float = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        context.resources.displayMetrics
    )

    fun pxToDp(context: Context, pixel: Float): Float = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_PX,
        pixel,
        context.resources.displayMetrics
    )

}