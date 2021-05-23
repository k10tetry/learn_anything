package com.codewithk10.learnanything.ui.dashboard.statistics

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.ui.base.BaseFragment

class StatisticsFragment : BaseFragment() {

    companion object {
        const val TAG = "StatisticsFragment"

        fun getInstance(): Fragment {
            return StatisticsFragment()
        }

        fun getInstance(bundle: Bundle): Fragment {
            val fragment = getInstance()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun setLayout(): Int {
        return R.layout.fragment_statistics
    }

    override fun init(view: View) {
    }

    override fun setUp() {

    }
}