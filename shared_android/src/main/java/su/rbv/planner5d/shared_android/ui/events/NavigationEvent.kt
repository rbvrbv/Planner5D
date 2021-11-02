package su.rbv.planner5d.shared_android.ui.events

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

interface NavigationEvent {

    fun navigate(fragment: Fragment)

    object Back : NavigationEvent {

        override fun navigate(fragment: Fragment) {
            fragment.findNavController().popBackStack()
        }
    }

}