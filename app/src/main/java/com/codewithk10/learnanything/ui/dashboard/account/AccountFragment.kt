package com.codewithk10.learnanything.ui.dashboard.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.ui.base.BaseFragment

class AccountFragment : BaseFragment() {

    private val TAG = "AccountFragment"

    override fun setLayout(): Int {
        return R.layout.fragment_account
    }

    override fun init(view: View) {
    }

    override fun getInstance(): Fragment {
        return AccountFragment()
    }

    override fun getInstance(bundle: Bundle): Fragment {
        val fragment = getInstance()
        fragment.arguments = bundle
        return fragment
    }
}