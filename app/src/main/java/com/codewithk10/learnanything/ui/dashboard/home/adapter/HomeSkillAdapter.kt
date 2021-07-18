package com.codewithk10.learnanything.ui.dashboard.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.codewithk10.learnanything.data.db.entity.Skill
import com.codewithk10.learnanything.databinding.ItemSkillBinding
import com.codewithk10.learnanything.ui.base.BaseRecycleAdapter
import com.codewithk10.learnanything.ui.base.BaseViewHolder

class HomeSkillAdapter(val context: Context) : BaseRecycleAdapter<Skill>(context) {
    var listener: OnSkillClickListener? = null

    interface OnSkillClickListener {
        fun onClickSkill(dataItem: Skill)
        fun onLongClickSkill(dataItem: Skill)
        fun onStartSkill(dataItem: Skill)
    }

    override fun getViewHolder(parent: ViewGroup): BaseViewHolder<Skill> {
        return HomeSkillViewHolder(
            ItemSkillBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    inner class HomeSkillViewHolder(private val binding: ItemSkillBinding) :
        BaseViewHolder<Skill>(binding.root) {
        override fun onBind(data: Skill) {
            binding.tvSkillTitle.text = data.skillTitle
            binding.tvSkillNote.text = data.skillNote
            binding.tvSkillCategory.text = data.skillCategory.categoryTitle

            binding.root.setOnClickListener {
                listener?.onClickSkill(data)
            }

            binding.root.setOnLongClickListener {
                listener?.onLongClickSkill(data)
                true
            }

            binding.ivSkillStart.setOnClickListener {
                listener?.onStartSkill(data)
            }
        }
    }
}