package com.codewithk10.learnanything.ui.dashboard.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.data.db.AppDatabase
import com.codewithk10.learnanything.data.db.entity.Skill
import com.codewithk10.learnanything.ui.base.BaseFragment
import com.codewithk10.learnanything.ui.dashboard.home.adapter.HomeSkillAdapter
import com.codewithk10.learnanything.ui.service.TimerService
import com.codewithk10.learnanything.utils.itemdecorator.HomeSkillItemDecorator
import com.codewithk10.learnanything.utils.notification.AppAlarmService
import com.google.android.material.appbar.MaterialToolbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeFragment : BaseFragment(), HomeSkillAdapter.OnSkillClickListener {

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
        recycleView = view.findViewById(R.id.rv_home_skill)
        materialToolbar = view.findViewById(R.id.toolbar)
        textViewToolbarTitle = view.findViewById(R.id.tv_toolbar_title)
        imageViewPremium = view.findViewById(R.id.iv_toolbar_premium)
        imageViewNotification = view.findViewById(R.id.iv_toolbar_notification)

        setUpListeners()
    }

    override fun setUp() {
        setUpToolbar()
        setUpAdapter()
        loadSkills()
    }

    private fun loadSkills() {
        AppDatabase.getDatabase(requireContext()).skillDao().getAllSkills()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    homeSkillAdapter.dataItemList = ArrayList(it)
                },
                {
                    it.printStackTrace()
                }
            )
    }

    private fun setUpAdapter() {
        homeSkillAdapter = HomeSkillAdapter(requireContext())
        homeSkillAdapter.listener = this
        recycleView.adapter = homeSkillAdapter
        recycleView.layoutManager = LinearLayoutManager(requireContext())
        recycleView.addItemDecoration(HomeSkillItemDecorator(requireContext()))
    }

    private fun setUpListeners() {
        imageViewPremium.setOnClickListener(onClickPremium)
        imageViewNotification.setOnClickListener(onClickNotification)
    }

    private val onClickPremium: (View) -> Unit = {

    }

    private val onClickNotification: (View) -> Unit = {

    }

    private fun setUpToolbar() {
        // TODO: 23/5/21 Set app user name
        textViewToolbarTitle.text = "Hi, Ketan"
    }

    override fun onClickSkill(dataItem: Skill) {
        AppAlarmService(requireContext()).apply {
            cancelScheduledAlarm(dataItem.skillNotification)
        }
    }

    override fun onLongClickSkill(dataItem: Skill) {
        AppDatabase.getDatabase(requireContext()).skillDao().deleteSkill(dataItem)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onClickSkill(dataItem)
            }, {
                it.printStackTrace()
            })
    }

    override fun onStartSkill(dataItem: Skill) {
        val intent = Intent(requireContext(), TimerService::class.java)
        intent.putExtra(TimerService.EXTRA_APP_NOTIFY, dataItem.skillNotification)
        requireContext().startService(intent)
    }
}