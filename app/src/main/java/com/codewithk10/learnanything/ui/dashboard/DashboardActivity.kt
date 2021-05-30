package com.codewithk10.learnanything.ui.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.codewithk10.bottomnavigationindicator.BottomNavigationIndicator
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.ui.base.BaseActivity
import com.codewithk10.learnanything.ui.dashboard.account.AccountFragment
import com.codewithk10.learnanything.ui.dashboard.home.HomeFragment
import com.codewithk10.learnanything.ui.dashboard.record.RecordFragment
import com.codewithk10.learnanything.ui.dashboard.statistics.StatisticsFragment
import com.codewithk10.learnanything.ui.skill.CreateSkillActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DashboardActivity : BaseActivity() {

    companion object {

        fun getInstance(context: Context): Intent {
            return Intent(context, DashboardActivity::class.java)
        }

        fun getInstance(context: Context, bundle: Bundle): Intent {
            val intent = getInstance(context)
            intent.putExtras(bundle)
            return intent
        }
    }

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var bottomViewSelectionBar: BottomNavigationIndicator
    private lateinit var floatingActionButton: FloatingActionButton

    override fun setLayout(): Int {
        return R.layout.activity_dashboard
    }

    override fun init() {
        initView()
        openFragment(HomeFragment.getInstance(), HomeFragment.TAG)
    }

    private fun initView() {
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomViewSelectionBar = findViewById(R.id.bottom_navigation_indicator)
        floatingActionButton = findViewById(R.id.fab_dashboard)
        initListener()
    }

    private fun initListener() {
        bottomNavigationView.setOnItemSelectedListener {
            onBottomNavigation(it)
            true
        }
        floatingActionButton.setOnClickListener {
            openCreateSkillActivity()
        }
    }

    private fun onBottomNavigation(item: MenuItem) {
        when (item.itemId) {
            R.id.menu_home -> {
                bottomViewSelectionBar.setSelectedIndex(0)
                openFragment(HomeFragment.getInstance(), HomeFragment.TAG)
            }
            R.id.menu_record -> {
                bottomViewSelectionBar.setSelectedIndex(1)
                openFragment(RecordFragment.getInstance(), RecordFragment.TAG)
            }
            R.id.menu_statistics -> {
                bottomViewSelectionBar.setSelectedIndex(3)
                openFragment(StatisticsFragment.getInstance(), StatisticsFragment.TAG)
            }
            R.id.menu_account -> {
                bottomViewSelectionBar.setSelectedIndex(4)
                openFragment(AccountFragment.getInstance(), AccountFragment.TAG)
            }
        }
    }

    private fun openCreateSkillActivity() {
        startActivity(CreateSkillActivity.getInstance(this))
    }

    private fun openFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_container, fragment, tag)
            disallowAddToBackStack()
            commit()
        }
    }
}