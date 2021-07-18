package com.codewithk10.learnanything.ui.dashboard.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codewithk10.learnanything.databinding.FragmentRecordBinding
import com.codewithk10.learnanything.ui.base.BaseFragment

class RecordFragment : BaseFragment() {

    companion object {
        const val TAG = "RecordFragment"

        fun getInstance(): Fragment {
            return RecordFragment()
        }

        fun getInstance(bundle: Bundle): Fragment {
            val fragment = getInstance()
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var binding: FragmentRecordBinding

    override fun setLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentRecordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun init() {
    }

    override fun setUp() {

    }
}