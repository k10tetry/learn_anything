package com.codewithk10.learnanything.ui.skill.adapter

import android.content.Context
import android.view.View
import android.widget.RadioButton
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.ui.base.BaseRecycleAdapter
import com.codewithk10.learnanything.ui.skill.data.TargetData

class SkillTargetAdapter(context: Context) : BaseRecycleAdapter<TargetData>(context) {

    private lateinit var radioButtonTarget: RadioButton
    var listener: OnTargetSelectedListener? = null
    var selectedTarget: TargetData? = null

    override fun setItemView(): Int {
        return R.layout.itemview_target
    }

    override fun initView(view: View) {
        radioButtonTarget = view.findViewById(R.id.rb_target)
    }

    override fun initDataItem(dataItem: TargetData) {
        radioButtonTarget.text = dataItem.targetTitle

        if (selectedTarget != null)
            radioButtonTarget.isChecked = selectedTarget == dataItem

        radioButtonTarget.setOnClickListener {
            selectedTarget = dataItem
            listener?.onSelect(dataItem)
        }
    }

    interface OnTargetSelectedListener {
        fun onSelect(dataItem: TargetData)
    }
}