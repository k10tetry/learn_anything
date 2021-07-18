package com.codewithk10.learnanything.ui.skill.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.ui.base.BaseRecycleAdapter
import com.codewithk10.learnanything.ui.skill.data.CategoryData

class SkillCategoryAdapter(context: Context) :
    BaseRecycleAdapter<CategoryData>(context) {

    private lateinit var textViewCategory: TextView
    private lateinit var imageViewCategory: ImageView
    private lateinit var imageViewSelectedCategory: ImageView
    private lateinit var itemview: View
    var selectedCategory: CategoryData? = null
    var listener: OnCategorySelectedListener? = null

    override fun setItemView(parent: ViewGroup): ViewGroup {
        return R.layout.item_category
    }

    override fun initView(view: View) {
        itemview = view
        textViewCategory = view.findViewById(R.id.tv_category_title)
        imageViewCategory = view.findViewById(R.id.iv_category_image)
        imageViewSelectedCategory = view.findViewById(R.id.iv_category_selection)
    }

    override fun initDataItem(dataItem: CategoryData) {
        textViewCategory.text = dataItem.categoryTitle
        imageViewCategory.setImageResource(dataItem.categoryImage)
        if (selectedCategory != null)
            imageViewSelectedCategory.visibility =
                if (selectedCategory == dataItem) View.VISIBLE else View.GONE

        itemview.setOnClickListener {
            selectedCategory = dataItem
            listener?.onSelectCategory(dataItem)
            notifyDataSetChanged()
        }
    }

    interface OnCategorySelectedListener {
        fun onSelectCategory(dataItem: CategoryData)
    }

}