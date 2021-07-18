package com.codewithk10.learnanything.ui.base

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecycleAdapter<T>(private val context: Context) :
    RecyclerView.Adapter<BaseViewHolder<T>>() {

    abstract fun getViewHolder(parent: ViewGroup): BaseViewHolder<T>

    var dataItemList = ArrayList<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return getViewHolder(parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.onBind(dataItemList[position])
    }

    override fun getItemCount(): Int {
        return dataItemList.size
    }

}