package com.codewithk10.learnanything.ui.dashboard.home

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.data.db.AppDatabase
import com.codewithk10.learnanything.ui.base.BaseFragment
import com.codewithk10.learnanything.ui.dashboard.home.adapter.HomeSkillAdapter
import com.codewithk10.learnanything.utils.itemdecorator.HomeSkillItemDecorator
import com.google.android.material.appbar.MaterialToolbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

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

    private lateinit var textViewToolbarTitle: TextView
    private lateinit var imageViewPremium: ImageView
    private lateinit var imageViewNotification: ImageView
    private lateinit var recycleView: RecyclerView
    private lateinit var materialToolbar: MaterialToolbar
    private lateinit var homeSkillAdapter: HomeSkillAdapter

    override fun setLayout(): Int {
        return R.layout.fragment_home
    }

    override fun init(view: View) {
        initView(view)
        setUpListeners()
        setUpAdapter()
        AppDatabase.getDatabase(requireContext()).skillDao().getAllSkills()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                homeSkillAdapter.dataItemList = ArrayList(it)
            }
    }

    private fun initView(view: View) {
        recycleView = view.findViewById(R.id.rv_home_skill)
        materialToolbar = view.findViewById(R.id.toolbar)
        textViewToolbarTitle = view.findViewById(R.id.tv_toolbar_title)
        imageViewPremium = view.findViewById(R.id.iv_toolbar_premium)
        imageViewNotification = view.findViewById(R.id.iv_toolbar_notification)

        setUpToolbar()
        setUpListeners()
    }

    private fun setUpAdapter() {
        homeSkillAdapter = HomeSkillAdapter(requireContext())
        recycleView.adapter = homeSkillAdapter
        recycleView.layoutManager = LinearLayoutManager(requireContext())
        recycleView.addItemDecoration(HomeSkillItemDecorator(requireContext()))
    }

    private fun setUpListeners() {

    }

    private fun setUpToolbar() {
        textViewToolbarTitle.text = "Hi, Ketan"

        imageViewPremium.setOnClickListener {
        }

        imageViewNotification.setOnClickListener {
        }
    }
}