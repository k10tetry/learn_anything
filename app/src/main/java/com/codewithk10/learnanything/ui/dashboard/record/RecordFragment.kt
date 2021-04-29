package com.codewithk10.learnanything.ui.dashboard.record

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.ui.base.BaseFragment
import com.codewithk10.learnanything.ui.dashboard.statistics.StatisticsFragment

class RecordFragment : BaseFragment() {

    private val TAG = "RecordFragment"

    override fun setLayout(): Int {
        return R.layout.fragment_record
    }

    override fun init(view: View) {
    }

    override fun getInstance(): Fragment {
        return RecordFragment()
    }

    override fun getInstance(bundle: Bundle): Fragment {
        return RecordFragment()
    }
}