package su.rbv.planner5d.main.events

import androidx.fragment.app.Fragment
import su.rbv.planner5d.R
import su.rbv.planner5d.shared_android.extensions.toast
import su.rbv.planner5d.shared_android.ui.events.ViewEvent

object ProjectErrorEvent : ViewEvent {

    override fun execute(fragment: Fragment) {
        fragment.context?.toast(R.string.projects_loading_error)
    }
}