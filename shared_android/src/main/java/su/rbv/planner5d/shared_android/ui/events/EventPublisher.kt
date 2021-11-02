package su.rbv.planner5d.shared_android.ui.events

import androidx.navigation.NavDirections
import dagger.Reusable
import su.rbv.planner5d.shared_android.base.BufferLiveData
import su.rbv.planner5d.shared_android.extensions.toNavigationEvent
import javax.inject.Inject

@Reusable
class EventPublisher @Inject constructor() {

    val viewEvent = BufferLiveData<ViewEvent>()

    val navEvent = BufferLiveData<NavigationEvent>()

    fun postViewEvents(vararg events: ViewEvent) = events.forEach(viewEvent::setValue)

    fun postNavEvents(vararg events: NavigationEvent) = events.forEach(navEvent::setValue)

    fun postNavEvents(vararg events: NavDirections) {
        postNavEvents(*events.map { it.toNavigationEvent() }.toTypedArray())
    }

}