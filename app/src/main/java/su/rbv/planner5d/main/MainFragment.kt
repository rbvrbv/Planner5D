package su.rbv.planner5d.main

import android.os.Bundle
import su.rbv.planner5d.BR
import su.rbv.planner5d.R
import su.rbv.planner5d.databinding.FragmentMainBinding
import su.rbv.planner5d.main.di.MainFragmentComponentHolder
import su.rbv.planner5d.main.mapper.ProjectDataToAdapterMapper
import su.rbv.planner5d.shared_android.di.UIComponent
import su.rbv.planner5d.shared_android.extensions.injectViewModel
import su.rbv.planner5d.shared_android.extensions.observeOnViewLifecycle
import su.rbv.planner5d.shared_android.ui.BaseFragment

internal class MainFragment : BaseFragment<MainFragmentViewModel>() {

    override val layoutId = R.layout.fragment_main
    override val dataBindingVariable = BR.vm
    override val viewModel by injectViewModel()

    private val adapterItemMapper by lazy { ProjectDataToAdapterMapper() }

    override fun diComponent(): UIComponent = MainFragmentComponentHolder.getComponent()

    override fun initViews(savedInstanceState: Bundle?) {
        super.initViews(savedInstanceState)

        binding<FragmentMainBinding> {
            mainRecycler.adapter =
                MainFragmentProjectsAdapter(viewModel).apply {
                    observeOnViewLifecycle(viewModel.projects) { projectDataList ->
                        projectDataList?.map(adapterItemMapper::invoke)?.let { adapterItems ->
                            addAll(adapterItems)
                        }
                    }
                }
        }
    }

}