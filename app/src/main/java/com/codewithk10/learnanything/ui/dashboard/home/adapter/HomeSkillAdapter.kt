package com.codewithk10.learnanything.ui.dashboard.home.adapter

import android.content.Context
import android.view.View
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.data.db.entity.Skill
import com.codewithk10.learnanything.ui.base.BaseRecycleAdapter

class HomeSkillAdapter(context: Context) : BaseRecycleAdapter<Skill>(context) {

    override fun setItemView(): Int {
        return R.layout.itemview_skill
    }

    override fun initView(view: View) {

    }

    override fun initDataItem(dataItem: Skill) {

    }
}