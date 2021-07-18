package com.codewithk10.learnanything.ui.dashboard.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codewithk10.learnanything.databinding.FragmentAccountBinding
import com.codewithk10.learnanything.ui.base.BaseFragment

class AccountFragment : BaseFragment() {

    companion object {
        const val TAG = "AccountFragment"

        fun getInstance(): Fragment {
            return AccountFragment()
        }

        fun getInstance(bundle: Bundle): Fragment {
            val fragment = getInstance()
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var fragmentAccountBinding: FragmentAccountBinding

    override fun setLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        fragmentAccountBinding = FragmentAccountBinding.inflate(inflater, container, false)
        return fragmentAccountBinding.root
    }

    override fun init() {
    }

    override fun setUp() {

    }
}