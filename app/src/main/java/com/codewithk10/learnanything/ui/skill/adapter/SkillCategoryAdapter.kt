package com.codewithk10.learnanything.ui.skill.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.ui.base.BaseRecycleAdapter

class SkillCategoryAdapter(private val context: Context) :
    BaseRecycleAdapter<CategoryData>(context) {

    private lateinit var textViewCategory: TextView
    private lateinit var imageViewCategory: ImageView
    private lateinit var imageViewSelectedCategory: ImageView
    private lateinit var itemview: View
    var selectedCategory = CategoryData.CAREER

    override fun setItemView(): Int {
        return R.layout.itemview_category
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
        imageViewSelectedCategory.visibility =
            if (selectedCategory == dataItem) View.VISIBLE else View.GONE

        itemview.setOnClickListener {
            selectedCategory = dataItem
            notifyDataSetChanged()
        }
    }

}