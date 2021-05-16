package com.codewithk10.learnanything.ui.base

import android.view.View

interface BaseRecycleAdapterListener<T> {
    fun initView(view: View, position: Int)
    fun initDataItem(dataItem: T)
}