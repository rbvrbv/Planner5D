package su.rbv.planner5d.main

import androidx.databinding.ViewDataBinding
import su.rbv.planner5d.R
import su.rbv.planner5d.BR
import su.rbv.planner5d.main.model.ProjectsAdapterItem
import su.rbv.planner5d.shared_android.ui.recycler.SingleItemLayoutDiffListAdapter

internal class MainFragmentProjectsAdapter(
    private val onProjectItemClickListener: OnProjectItemClickListener
) : SingleItemLayoutDiffListAdapter<ProjectsAdapterItem>(

        dataBindingVariable = BR.value,
        itemLayoutId = R.layout.li_projects
) {

    override fun setVariables(binding: ViewDataBinding, item: ProjectsAdapterItem) {
        super.setVariables(binding, item)
        binding.setVariable(BR.projectItemClickListener, onProjectItemClickListener)
    }

}
