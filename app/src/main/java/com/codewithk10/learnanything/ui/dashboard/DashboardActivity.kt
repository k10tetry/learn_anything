package com.codewithk10.learnanything.ui.dashboard

import androidx.fragment.app.Fragment
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.ui.base.BaseActivity
import com.codewithk10.learnanything.ui.dashboard.home.HomeFragment

class DashboardActivity : BaseActivity() {

    val TAG = "DashboardActivity"

    override fun setLayout(): Int {
        return R.layout.activity_dashboard;
    }

    override fun init() {
        val homeFragment = HomeFragment()
        openFragment(homeFragment, homeFragment.TAG);
    }

    private fun openFragment(fragment: Fragment, tag: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_container, fragment, tag)
        fragmentTransaction.disallowAddToBackStack()
        fragmentTransaction.commit()
    }
}