package com.codewithk10.learnanything.utils.itemdecorator

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.codewithk10.learnanything.utils.AppUtil

class HomeSkillItemDecorator(private val context: Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val bottom = when (parent.getChildAdapterPosition(view)) {
            parent.adapter?.itemCount?.minus(1) -> 16f
            else -> 0f
        }

        outRect.left = AppUtil.dpToPx(context, 16f).toInt()
        outRect.right = AppUtil.dpToPx(context, 16f).toInt()
        outRect.top = AppUtil.dpToPx(context, 16f).toInt()
        outRect.bottom = AppUtil.dpToPx(context, bottom).toInt()
    }
}
