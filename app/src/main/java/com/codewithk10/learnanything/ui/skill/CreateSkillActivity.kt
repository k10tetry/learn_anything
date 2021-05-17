package com.codewithk10.learnanything.ui.skill

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
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
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

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
    private lateinit var textViewReminder: TextView
    private lateinit var textViewSound: TextView
    private lateinit var textViewTarget: TextView
    private lateinit var editTextTitle: EditText
    private lateinit var editTextNote: EditText

    override fun setLayout(): Int {
        return R.layout.activity_create_skill
    }

    override fun init() {
        setNavigationColor(R.color.colorBackground)
        initView()
        setUpAdapter()
    }

    private fun initView() {
        recycleView = findViewById(R.id.rv_create_skill)
        materialToolbar = findViewById(R.id.toolbar)
        textViewToolbarTitle = findViewById(R.id.tv_toolbar_title)
        imageViewBack = findViewById(R.id.iv_toolbar_back)
        imageViewSave = findViewById(R.id.iv_toolbar_action)

        editTextTitle = findViewById(R.id.edt_skill_title)
        editTextNote = findViewById(R.id.edt_skill_note)
        textViewReminder = findViewById(R.id.tv_skill_reminder)
        textViewSound = findViewById(R.id.tv_skill_sound)
        textViewTarget = findViewById(R.id.tv_skill_target)

        setUpToolbar()
        setUpListeners()
    }

    private fun setUpAdapter() {
        skillCategoryAdapter = SkillCategoryAdapter(this)
        skillCategoryAdapter.dataItemList = ArrayList(CategoryData.values().toList())
        recycleView.adapter = skillCategoryAdapter
        recycleView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recycleView.addItemDecoration(SkillCategoryItemDecorator(this))
    }

    private fun setUpListeners() {
        textViewReminder.setOnClickListener {
            val materialTimePicker = MaterialTimePicker.Builder().setHour(7).setMinute(0)
                .setTimeFormat(TimeFormat.CLOCK_12H).setTitleText("Set Reminder").build()
            materialTimePicker.show(supportFragmentManager, null)

            materialTimePicker.addOnPositiveButtonClickListener {
                toast(materialTimePicker.hour.toString())
            }
        }

        textViewSound.setOnClickListener {
            // TODO: 17/5/21 Open sound selection dialog
        }

        textViewTarget.setOnClickListener {
            val targetDialog = SkillTargetDialog()
            targetDialog.show(supportFragmentManager, null)
        }
    }

    private fun setUpToolbar() {
        textViewToolbarTitle.text = getString(R.string.toolbar_add_skill)

        imageViewBack.setOnClickListener {
            setResult(RESULT_CANCELED)
            onBackPressed()
        }

        imageViewSave.setOnClickListener {
            saveSkill()
        }
    }

    private fun saveSkill() {

        if (isInvalid()) {
            return
        }


    }

    private fun isInvalid(): Boolean {

        val skillTitle = editTextTitle.text.toString().trim()

        if (TextUtils.isEmpty(skillTitle)) {
            toast("Skill title required!")
            return true
        }

        return false
    }
}