package su.rbv.planner5d.preview.events

import androidx.fragment.app.Fragment
import su.rbv.planner5d.core_projects.domain.model.ProjectPreviewData
import su.rbv.planner5d.shared_android.ui.events.ViewEvent

class PreviewDataEvent(val data: ProjectPreviewData, val floorNameToDraw: String): ViewEvent {

    override fun execute(fragment: Fragment) {}

}