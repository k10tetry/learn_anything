package com.codewithk10.learnanything.ui.dashboard.statistics

import android.view.View
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.ui.base.BaseFragment

class StatisticsFragment : BaseFragment() {

    companion object {
        const val TAG = "StatisticsFragment"
    }

    override fun setLayout(): Int {
        return R.layout.fragment_statistics
    }

    override fun init(view: View) {
    }
}