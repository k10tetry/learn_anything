package com.codewithk10.learnanything.ui.dashboard.home.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.data.db.entity.Skill
import com.codewithk10.learnanything.ui.base.BaseRecycleAdapter

class HomeSkillAdapter(context: Context) : BaseRecycleAdapter<Skill>(context) {

    private lateinit var textViewSkillTitle: TextView
    private lateinit var textViewSkillNote: TextView
    private lateinit var textViewSkillCategory: TextView
    private lateinit var itemview: View
    private lateinit var imageViewStart: ImageView
    var listener: OnSkillClickListener? = null

    override fun setItemView(): Int {
        return R.layout.itemview_skill
    }

    override fun initView(view: View) {
        itemview = view
        textViewSkillTitle = view.findViewById(R.id.tv_skill_title)
        textViewSkillNote = view.findViewById(R.id.tv_skill_note)
        textViewSkillCategory = view.findViewById(R.id.tv_skill_category)
        imageViewStart = view.findViewById(R.id.iv_skill_start)
    }

    override fun initDataItem(dataItem: Skill) {
        textViewSkillTitle.text = dataItem.skillTitle
        textViewSkillNote.text = dataItem.skillNote
        textViewSkillCategory.text = dataItem.skillCategory.categoryTitle

        itemview.setOnClickListener {
            listener?.onClickSkill(dataItem)
        }

        itemview.setOnLongClickListener {
            listener?.onLongClickSkill(dataItem)
            true
        }

        imageViewStart.setOnClickListener {
            listener?.onStartSkill(dataItem)
        }
    }

    interface OnSkillClickListener {
        fun onClickSkill(dataItem: Skill)
        fun onLongClickSkill(dataItem: Skill)
        fun onStartSkill(dataItem: Skill)
    }
}