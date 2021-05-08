package com.codewithk10.learnanything.ui.dashboard.home

import android.view.View
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.ui.base.BaseFragment

class HomeFragment : BaseFragment() {

    companion object {
        const val TAG = "HomeFragment"
    }

    override fun setLayout(): Int {
        return R.layout.fragment_home
    }

    override fun init(view: View) {
    }
}