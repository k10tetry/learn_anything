package com.codewithk10.learnanything.ui.skill

import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codewithk10.learnanything.R
import com.codewithk10.learnanything.data.db.AppDatabase
import com.codewithk10.learnanything.data.db.entity.Skill
import com.codewithk10.learnanything.ui.base.BaseActivity
import com.codewithk10.learnanything.ui.skill.adapter.SkillCategoryAdapter
import com.codewithk10.learnanything.ui.skill.data.CategoryData
import com.codewithk10.learnanything.ui.skill.data.TargetData
import com.codewithk10.learnanything.utils.itemdecorator.SkillCategoryItemDecorator
import com.codewithk10.learnanything.utils.notification.AppAlarmService
import com.codewithk10.learnanything.utils.notification.AppNotify
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.joda.time.DateTime
import org.joda.time.LocalTime
import org.joda.time.format.DateTimeFormat

class CreateSkillActivity : BaseActivity(), SkillCategoryAdapter.OnCategorySelectedListener {

    companion object {

        private const val DEFAULT_NOTIFICATION_HOUR = 19
        private const val DEFAULT_NOTIFICATION_MINUTE = 0

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

    private lateinit var notificationSoundTitle: String
    private lateinit var notificationSoundUri: Uri
    private lateinit var selectedNotificationTime: LocalTime
    private var selectedCategory: CategoryData = CategoryData.CAREER
    private var selectedTarget: TargetData = TargetData.TWENTY_ONE

    override fun setLayout(): Int {
        return R.layout.activity_create_skill
    }

    override fun init() {
        setNavigationColor(R.color.colorBackground)
        initView()
        setUpAdapter()
        setUpNotificationReminder()
        setUpNotification()
        setTargetValue()
    }

    private fun setUpNotificationReminder() {
        selectedNotificationTime = LocalTime(DEFAULT_NOTIFICATION_HOUR, DEFAULT_NOTIFICATION_MINUTE)
        setNotificationTime()
    }

    private fun setNotificationTime() {
        textViewReminder.text = DateTimeFormat.shortTime().print(selectedNotificationTime)
    }

    private fun setUpNotification() {
        setUpDefaultNotification()
        setNotificationSound()
    }

    private fun setUpDefaultNotification() {
        notificationSoundUri =
            RingtoneManager.getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_NOTIFICATION)
        notificationSoundTitle =
            RingtoneManager.getRingtone(this, notificationSoundUri).getTitle(this)
    }

    private fun setNotificationSound() {
        textViewSound.text = notificationSoundTitle
    }

    private fun setTargetValue() {
        textViewTarget.text = selectedTarget.targetTitle
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
        skillCategoryAdapter.selectedCategory = selectedCategory
        skillCategoryAdapter.listener = this
        recycleView.adapter = skillCategoryAdapter
        recycleView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recycleView.addItemDecoration(SkillCategoryItemDecorator(this))
    }

    private fun setUpListeners() {
        textViewReminder.setOnClickListener {
            chooseNotificationReminder()
        }

        textViewSound.setOnClickListener {
            chooseNotificationSound()
        }

        textViewTarget.setOnClickListener {
            chooseSkillTarget()
        }
    }

    private fun chooseSkillTarget() {
        SkillTargetDialog(selectedTarget).apply {
            show(supportFragmentManager, SkillTargetDialog.TAG)
        }
    }

    private fun chooseNotificationReminder() {

        if (supportFragmentManager.findFragmentByTag("MaterialTimePicker") != null) {
            return
        }

        val materialTimePicker = MaterialTimePicker.Builder().apply {
            setHour(selectedNotificationTime.hourOfDay)
            setMinute(selectedNotificationTime.minuteOfHour)
            setTimeFormat(TimeFormat.CLOCK_12H)
            setTitleText("Set Reminder")
        }.build()

        materialTimePicker.addOnPositiveButtonClickListener {
            selectedNotificationTime = LocalTime(materialTimePicker.hour, materialTimePicker.minute)
            setNotificationTime()
        }

        materialTimePicker.show(supportFragmentManager, "MaterialTimePicker")
    }

    private fun setUpToolbar() {
        textViewToolbarTitle.text = getString(R.string.toolbar_add_skill)

        imageViewBack.setOnClickListener {
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

        val skillTitle = editTextTitle.text.toString().trim()
        val skillNote = editTextNote.text.toString().trim()

        val skillNotify = AppNotify(
            LocalTime.now().millisOfDay,
            skillTitle,
            skillNote,
            getNotificationReminderTime(),
            notificationSoundTitle,
            notificationSoundUri.toString()
        )

        val skillObject =
            Skill(
                skillTitle,
                skillNote,
                skillNotify,
                selectedTarget,
                selectedCategory,
                DateTime.now().millis
            )

        addSkillToRoom(skillObject)
        AppAlarmService(this).apply {
            createScheduledAlarm(skillNotify)
        }
    }

    private fun getNotificationReminderTime(): Long {
        var notificationTime = selectedNotificationTime.toDateTimeToday().millis
        if (selectedNotificationTime.isBefore(LocalTime.now())) {
            notificationTime = selectedNotificationTime.toDateTimeToday().plusDays(1).millis
        }
        return notificationTime
    }

    private fun addSkillToRoom(skillObject: Skill) {
        AppDatabase.getDatabase(this).skillDao().addSkill(skillObject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                finish()
            }, {
                log(it.message.toString())
            })
    }

    private fun isInvalid(): Boolean {

        val skillTitle = editTextTitle.text.toString().trim()
        val skillNote = editTextNote.text.toString().trim()

        if (skillTitle.isEmpty()) {
            toast("Please add skill title")
            return true
        }

        if (skillNote.isEmpty()) {
            toast("Please add skill note")
            return true
        }

        return false
    }

    private fun chooseNotificationSound() {
        val intent = Intent(RingtoneManager.ACTION_RINGTONE_PICKER).apply {
            putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_NOTIFICATION)
            putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Sound")
            putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, false)
            putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, notificationSoundUri)
        }
        notificationSoundResult.launch(intent)
    }

    private val notificationSoundResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val uri =
                    result.data?.getParcelableExtra<Uri>(RingtoneManager.EXTRA_RINGTONE_PICKED_URI)
                uri?.let {
                    notificationSoundUri = it
                    val notificationTone = RingtoneManager.getRingtone(this, uri)
                    notificationSoundTitle = notificationTone.getTitle(this)
                    setNotificationSound()
                }
            }
        }

    internal fun onTargetSelected(targetData: TargetData) {
        selectedTarget = targetData
        setTargetValue()
    }

    override fun onSelectCategory(dataItem: CategoryData) {
        selectedCategory = dataItem
    }
}