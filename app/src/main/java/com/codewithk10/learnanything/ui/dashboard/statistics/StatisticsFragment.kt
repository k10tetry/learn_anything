package com.codewithk10.learnanything.ui.dashboard.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codewithk10.learnanything.databinding.FragmentStatisticsBinding
import com.codewithk10.learnanything.ui.base.BaseFragment

class StatisticsFragment : BaseFragment() {

    companion object {
        const val TAG = "StatisticsFragment"

        fun getInstance(): Fragment {
            return StatisticsFragment()
        }

        fun getInstance(bundle: Bundle): Fragment {
            val fragment = getInstance()
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var binding: FragmentStatisticsBinding

    override fun setLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun init() {
    }

    override fun setUp() {

    }
}