package com.codewithk10.learnanything.ui.dashboard

import androidx.fragment.app.Fragment
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.ui.base.BaseActivity
import com.codewithk10.learnanything.ui.dashboard.home.HomeFragment
import com.codewithk10.learnanything.view.BottomViewSelectionBar
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardActivity : BaseActivity() {

    companion object {
        const val TAG = "DashboardActivity"
    }

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var bottomViewSelectionBar: BottomViewSelectionBar

    override fun setLayout(): Int {
        return R.layout.activity_dashboard;
    }

    override fun init() {
        initView()
        openFragment(HomeFragment(), HomeFragment.TAG);
    }

    private fun initView() {
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomViewSelectionBar = findViewById(R.id.bottom_selection_bar)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val selectedIndex = when (item.itemId) {
                R.id.menu_home -> 0
                R.id.menu_record -> 1
                R.id.menu_statistics -> 3
                R.id.menu_account -> 4
                else -> 0
            }
            bottomViewSelectionBar.animate(selectedIndex)
            true
        }
    }

    private fun openFragment(fragment: Fragment, tag: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_container, fragment, tag)
        fragmentTransaction.disallowAddToBackStack()
        fragmentTransaction.commit()
    }
}