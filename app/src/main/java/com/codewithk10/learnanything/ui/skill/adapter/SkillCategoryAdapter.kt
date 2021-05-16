package com.codewithk10.learnanything.ui.skill.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.ui.base.BaseRecycleAdapter

class SkillCategoryAdapter(val context: Context) : BaseRecycleAdapter<CategoryData>(context) {

    private lateinit var textViewCategory: TextView
    private lateinit var imageViewCategory: ImageView
    private lateinit var imageViewSelectedCategory: ImageView
    var selectedCategory = CategoryData.CAREER

    override fun setItemView(): Int {
        return R.layout.itemview_category
    }

    override fun initView(view: View, position: Int) {
        textViewCategory = view.findViewById(R.id.tv_category_title)
        imageViewCategory = view.findViewById(R.id.iv_category_image)
        imageViewSelectedCategory = view.findViewById(R.id.iv_category_selection)

        view.setOnClickListener {
            selectedCategory = dataItemList[position]
            notifyDataSetChanged()
        }
    }

    override fun initDataItem(dataItem: CategoryData) {
        textViewCategory.text = dataItem.categoryTitle
        imageViewCategory.setImageResource(dataItem.categoryImage)
        imageViewSelectedCategory.visibility =
            if (selectedCategory == dataItem) View.VISIBLE else View.GONE
    }

}