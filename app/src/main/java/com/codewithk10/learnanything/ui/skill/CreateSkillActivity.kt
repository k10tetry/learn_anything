package com.codewithk10.learnanything.ui.skill

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.ui.base.BaseActivity
import com.google.android.material.appbar.MaterialToolbar

class CreateSkillActivity : BaseActivity() {

    companion object {
        const val TAG = "CreateSkillActivity"

        fun getInstance(context: Context): Intent {
            return Intent(context, CreateSkillActivity::class.java)
        }

        fun getInstance(context: Context, bundle: Bundle): Intent {
            val intent = getInstance(context)
            intent.putExtras(bundle)
            return intent
        }
    }

    private lateinit var materialToolbar: MaterialToolbar

    override fun setLayout(): Int {
        return R.layout.activity_create_skill
    }

    override fun init() {
        initView()
    }

    private fun initView() {
        materialToolbar = findViewById(R.id.m_toolbar)
        materialToolbar.setNavigationOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            onBackPressed()
        }
    }
}