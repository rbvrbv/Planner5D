package su.rbv.planner5d.preview

import su.rbv.planner5d.BR
import su.rbv.planner5d.R
import su.rbv.planner5d.core_projects.domain.model.ProjectPreviewData
import su.rbv.planner5d.databinding.FragmentPreviewBinding
import su.rbv.planner5d.preview.di.PreviewFragmentComponentHolder
import su.rbv.planner5d.preview.events.PreviewDataEvent
import su.rbv.planner5d.shared_android.di.UIComponent
import su.rbv.planner5d.shared_android.extensions.injectViewModel
import su.rbv.planner5d.shared_android.ui.BaseFragment
import su.rbv.planner5d.shared_android.ui.events.ViewEvent

internal class PreviewFragment : BaseFragment<PreviewFragmentViewModel>() {

    override val layoutId = R.layout.fragment_preview
    override val dataBindingVariable = BR.vm
    override val viewModel by injectViewModel()

    override fun diComponent(): UIComponent = PreviewFragmentComponentHolder.getComponent()

    override fun handleEvent(event: ViewEvent): Boolean =
        when (event) {
            is PreviewDataEvent -> setPreviewData(event.data, event.floorNameToDraw)
            else -> false
        }

    private fun setPreviewData(data: ProjectPreviewData, floorNameToDraw: String): Boolean {
        binding<FragmentPreviewBinding> {
            previewView.setData(data, floorNameToDraw)
        }
        return true
    }

}