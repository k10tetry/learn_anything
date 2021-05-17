package com.codewithk10.learnanything.ui.base

import android.view.View

interface BaseRecycleAdapterListener<T> {
    fun initView(view: View)
    fun initDataItem(dataItem: T)
}