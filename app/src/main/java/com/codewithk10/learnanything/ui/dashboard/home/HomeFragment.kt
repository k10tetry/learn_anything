package com.codewithk10.learnanything.ui.dashboard.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.ui.base.BaseFragment

class HomeFragment : BaseFragment() {

    companion object {
        const val TAG = "HomeFragment"

        fun getInstance(): Fragment {
            return HomeFragment()
        }

        fun getInstance(bundle: Bundle): Fragment {
            val fragment = getInstance()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun setLayout(): Int {
        return R.layout.fragment_home
    }

    override fun init(view: View) {
    }
}