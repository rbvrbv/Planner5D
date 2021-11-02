package su.rbv.planner5d.shared_android.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import su.rbv.planner5d.shared_android.ui.events.NavigationEvent

fun NavDirections.toNavigationEvent(
    extras: Navigator.Extras? = null,
    navOptions: NavOptions? = null
) = object : NavigationEvent {

    override fun navigate(fragment: Fragment) {

        when (extras) {

            null -> fragment.findNavController()
                .navigate(this@toNavigationEvent.actionId, this@toNavigationEvent.arguments, navOptions)

            else -> fragment.findNavController()
                .navigate(this@toNavigationEvent.actionId, this@toNavigationEvent.arguments, navOptions, extras)
        }
    }
}