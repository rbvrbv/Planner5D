package su.rbv.planner5d.preview.events

import androidx.fragment.app.Fragment
import su.rbv.planner5d.R
import su.rbv.planner5d.shared_android.extensions.toast
import su.rbv.planner5d.shared_android.ui.events.ViewEvent

object PreviewLoadingErrorEvent : ViewEvent {

    override fun execute(fragment: Fragment) {
        fragment.context?.toast(R.string.preview_loading_error)
    }
}