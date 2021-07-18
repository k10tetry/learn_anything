package com.codewithk10.learnanything.ui.skill.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.codewithk10.learnanything.databinding.ItemTargetBinding
import com.codewithk10.learnanything.ui.base.BaseRecycleAdapter
import com.codewithk10.learnanything.ui.base.BaseViewHolder
import com.codewithk10.learnanything.ui.skill.data.TargetData

class SkillTargetAdapter(val context: Context) : BaseRecycleAdapter<TargetData>(context) {

    var listener: OnTargetSelectedListener? = null
    var selectedTarget: TargetData? = null

    interface OnTargetSelectedListener {
        fun onSelect(dataItem: TargetData)
    }

    override fun getViewHolder(parent: ViewGroup): BaseViewHolder<TargetData> {
        return SkillTargetViewHolder(
            ItemTargetBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    inner class SkillTargetViewHolder(private val binding: ItemTargetBinding) :
        BaseViewHolder<TargetData>(binding.root) {
        override fun onBind(data: TargetData) {
            binding.rbTarget.text = data.targetTitle

            if (selectedTarget != null)
                binding.rbTarget.isChecked = selectedTarget == data

            binding.rbTarget.setOnClickListener {
                selectedTarget = data
                listener?.onSelect(data)
            }
        }
    }
}