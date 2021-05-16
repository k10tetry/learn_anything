package com.codewithk10.learnanything.ui.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecycleAdapter<T>(private val context: Context) :
    RecyclerView.Adapter<BaseViewHolder>(), BaseRecycleAdapterListener<T> {

    @LayoutRes
    abstract fun setItemView(): Int

    var dataItemList = ArrayList<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(LayoutInflater.from(context).inflate(setItemView(), parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        initView(holder.itemView, holder.adapterPosition)
        initDataItem(dataItemList[position])
    }

    override fun getItemCount(): Int {
        return dataItemList.size
    }

}