package com.codewithk10.learnanything.ui.skill

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.ui.base.BaseActivity
import com.codewithk10.learnanything.ui.skill.adapter.CategoryData
import com.codewithk10.learnanything.ui.skill.adapter.SkillCategoryAdapter
import com.codewithk10.learnanything.utils.itemdecorator.SkillCategoryItemDecorator
import com.google.android.material.appbar.MaterialToolbar

class CreateSkillActivity : BaseActivity() {

    companion object {

        fun getInstance(context: Context): Intent {
            return Intent(context, CreateSkillActivity::class.java)
        }

        fun getInstance(context: Context, bundle: Bundle): Intent {
            val intent = getInstance(context)
            intent.putExtras(bundle)
            return intent
        }
    }

    private lateinit var textViewToolbarTitle: TextView
    private lateinit var imageViewBack: ImageView
    private lateinit var imageViewSave: ImageView
    private lateinit var recycleView: RecyclerView
    private lateinit var materialToolbar: MaterialToolbar
    private lateinit var skillCategoryAdapter: SkillCategoryAdapter

    override fun setLayout(): Int {
        return R.layout.activity_create_skill
    }

    override fun init() {
        setNavigationColor(R.color.colorBackground)
        initView()
        setUpAdapter()
    }

    private fun setUpAdapter() {
        skillCategoryAdapter = SkillCategoryAdapter(this)
        skillCategoryAdapter.dataItemList = ArrayList(CategoryData.values().toList())
        recycleView.adapter = skillCategoryAdapter
        recycleView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recycleView.addItemDecoration(SkillCategoryItemDecorator(this))
    }

    private fun initView() {
        recycleView = findViewById(R.id.rv_create_skill)
        materialToolbar = findViewById(R.id.toolbar)
        textViewToolbarTitle = findViewById(R.id.tv_toolbar_title)
        imageViewBack = findViewById(R.id.iv_toolbar_back)
        imageViewSave = findViewById(R.id.iv_toolbar_action)

        textViewToolbarTitle.text = getString(R.string.toolbar_add_skill)

        imageViewBack.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            onBackPressed()
        }

        imageViewSave.setOnClickListener {
            toast("Save Skill")
        }
    }
}