package com.codewithk10.learnanything.utils.itemdecorator

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.codewithk10.learnanything.utils.Util

class HomeSkillItemDecorator(private val context: Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val bottom = when (parent.getChildAdapterPosition(view)) {
            parent.adapter?.itemCount?.minus(1) -> 24f
            else -> 0f
        }

        outRect.left = Util.dpToPx(context, 24f).toInt()
        outRect.right = Util.dpToPx(context, 24f).toInt()
        outRect.top = Util.dpToPx(context, 16f).toInt()
        outRect.bottom = Util.dpToPx(context, bottom).toInt()
    }
}
