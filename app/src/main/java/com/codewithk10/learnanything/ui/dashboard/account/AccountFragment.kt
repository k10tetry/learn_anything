package com.codewithk10.learnanything.ui.dashboard.account

import android.view.View
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.ui.base.BaseFragment

class AccountFragment : BaseFragment() {

    companion object {
        const val TAG = "AccountFragment"
    }

    override fun setLayout(): Int {
        return R.layout.fragment_account
    }

    override fun init(view: View) {
    }
}