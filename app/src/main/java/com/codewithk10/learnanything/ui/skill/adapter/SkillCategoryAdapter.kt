package com.codewithk10.learnanything.ui.skill.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codewithk10.learnanything.databinding.ItemCategoryBinding
import com.codewithk10.learnanything.ui.base.BaseRecycleAdapter
import com.codewithk10.learnanything.ui.base.BaseViewHolder
import com.codewithk10.learnanything.ui.skill.data.CategoryData

class SkillCategoryAdapter(val context: Context) :
    BaseRecycleAdapter<CategoryData>(context) {
    var selectedCategory: CategoryData? = null
    var listener: OnCategorySelectedListener? = null

    interface OnCategorySelectedListener {
        fun onSelectCategory(dataItem: CategoryData)
    }

    override fun getViewHolder(parent: ViewGroup): BaseViewHolder<CategoryData> {
        return SkillCategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    inner class SkillCategoryViewHolder(private val binding: ItemCategoryBinding) :
        BaseViewHolder<CategoryData>(binding.root) {
        override fun onBind(data: CategoryData) {
            binding.tvCategoryTitle.text = data.categoryTitle
            binding.ivCategoryImage.setImageResource(data.categoryImage)
            if (selectedCategory != null) binding.ivCategorySelection.visibility =
                if (selectedCategory == data) View.VISIBLE else View.GONE

            binding.root.setOnClickListener {
                selectedCategory = data
                listener?.onSelectCategory(data)
                notifyDataSetChanged()
            }
        }
    }

}