package com.codewithk10.learnanything.ui.dashboard.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.ui.base.BaseFragment

class HomeFragment : BaseFragment() {

    val TAG = "HomeFragment"

    override fun setLayout(): Int {
        return R.layout.fragment_home
    }

    override fun init(view: View) {
    }

    override fun getInstance(): Fragment {
        return HomeFragment()
    }

    override fun getInstance(bundle: Bundle): Fragment {
        return HomeFragment()
    }
}