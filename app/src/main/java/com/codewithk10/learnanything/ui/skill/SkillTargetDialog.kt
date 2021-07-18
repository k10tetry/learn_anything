package com.codewithk10.learnanything.ui.skill

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codewithk10.learnanything.databinding.DialogSkillTargetBinding
import com.codewithk10.learnanything.ui.base.BaseDialog
import com.codewithk10.learnanything.ui.skill.adapter.SkillTargetAdapter
import com.codewithk10.learnanything.ui.skill.data.TargetData

class SkillTargetDialog(private val selectedTarget: TargetData) : BaseDialog(),
    SkillTargetAdapter.OnTargetSelectedListener {

    companion object {
        const val TAG = "SkillTargetDialog"
    }

    private lateinit var recycleView: RecyclerView
    private lateinit var skillTargetAdapter: SkillTargetAdapter
    private var createSkillActivity: CreateSkillActivity? = null
    private lateinit var binding: DialogSkillTargetBinding

    override fun setLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = DialogSkillTargetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView(view: View) {
        recycleView = binding.rvSkillTarget
    }

    override fun init() {
        setUpAdapter()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CreateSkillActivity) {
            createSkillActivity = context
        }
    }

    private fun setUpAdapter() {
        skillTargetAdapter = SkillTargetAdapter(requireContext())
        skillTargetAdapter.dataItemList = ArrayList(TargetData.values().toList())
        skillTargetAdapter.selectedTarget = selectedTarget
        skillTargetAdapter.listener = this
        recycleView.adapter = skillTargetAdapter
        recycleView.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun onSelect(dataItem: TargetData) {
        createSkillActivity?.onTargetSelected(dataItem)
        dismiss()
    }


}