package su.rbv.planner5d.preview.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import su.rbv.planner5d.R
import su.rbv.planner5d.preview.PreviewFragmentArgs
import su.rbv.planner5d.shared_android.ui.events.NavigationEvent

class PreviewNextNavEvent(private val hash: String): NavigationEvent {

    override fun navigate(fragment: Fragment) {
        fragment.findNavController().navigate(PreviewNavDirection(hash))
    }

    private class PreviewNavDirection(private val hash: String) : NavDirections {

        override fun getActionId(): Int = R.id.action_preview_next

        override fun getArguments(): Bundle = PreviewFragmentArgs(hash).toBundle()
    }

}