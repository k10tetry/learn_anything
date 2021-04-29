package com.codewithk10.learnanything.ui.dashboard.statistics

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.ui.base.BaseFragment
import com.codewithk10.learnanything.ui.dashboard.home.HomeFragment

class StatisticsFragment : BaseFragment() {

    private val TAG = "StatisticsFragment"

    override fun setLayout(): Int {
        return R.layout.fragment_home
    }

    override fun init(view: View) {
    }

    override fun getInstance(): Fragment {
        return StatisticsFragment()
    }

    override fun getInstance(bundle: Bundle): Fragment {
        return StatisticsFragment()
    }
}