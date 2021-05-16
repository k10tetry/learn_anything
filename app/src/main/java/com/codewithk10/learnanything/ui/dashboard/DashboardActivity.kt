package com.codewithk10.learnanything.ui.dashboard

import android.app.Activity
import android.content.Intent
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.ui.base.BaseActivity
import com.codewithk10.learnanything.ui.dashboard.account.AccountFragment
import com.codewithk10.learnanything.ui.dashboard.home.HomeFragment
import com.codewithk10.learnanything.ui.dashboard.record.RecordFragment
import com.codewithk10.learnanything.ui.dashboard.statistics.StatisticsFragment
import com.codewithk10.learnanything.ui.skill.CreateSkillActivity
import com.codewithk10.learnanything.view.BottomViewSelectionBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DashboardActivity : BaseActivity() {

    companion object {
        private const val REQUEST_CODE_CREATE_SKILL = 101
    }

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var bottomViewSelectionBar: BottomViewSelectionBar
    private lateinit var floatingActionButton: FloatingActionButton

    override fun setLayout(): Int {
        return R.layout.activity_dashboard;
    }

    override fun init() {
        initView()
        openFragment(HomeFragment.getInstance(), HomeFragment.TAG);
    }

    private fun initView() {
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomViewSelectionBar = findViewById(R.id.bottom_selection_bar)
        floatingActionButton = findViewById(R.id.fab_dashboard)
        initListener()
    }

    private fun initListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_CREATE_SKILL -> {
                    // TODO: 9/5/21 Refresh skill listings
                }
            }
        }
    }

    private fun openCreateSkillActivity() {
        startActivityForResult(CreateSkillActivity.getInstance(this), REQUEST_CODE_CREATE_SKILL)
    }

    private fun openFragment(fragment: Fragment, tag: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_container, fragment, tag)
        fragmentTransaction.disallowAddToBackStack()
        fragmentTransaction.commit()
    }
}