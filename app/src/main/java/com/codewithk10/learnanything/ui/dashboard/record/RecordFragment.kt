package com.codewithk10.learnanything.ui.dashboard.record

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.ui.base.BaseFragment
import com.codewithk10.learnanything.ui.dashboard.home.HomeFragment
import com.codewithk10.learnanything.ui.dashboard.statistics.StatisticsFragment

class RecordFragment : BaseFragment() {

    companion object {
        const val TAG = "RecordFragment"

        fun getInstance(): Fragment {
            return RecordFragment()
        }

        fun getInstance(bundle: Bundle): Fragment {
            val fragment = getInstance()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun setLayout(): Int {
        return R.layout.fragment_record
    }

    override fun init(view: View) {
    }

    override fun setUp() {

    }
}