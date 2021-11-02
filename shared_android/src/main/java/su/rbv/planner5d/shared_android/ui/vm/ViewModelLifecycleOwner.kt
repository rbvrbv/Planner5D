package su.rbv.planner5d.shared_android.ui.vm

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import dagger.Reusable
import javax.inject.Inject

@Reusable
class ViewModelLifecycleOwner @Inject constructor() : LifecycleOwner {

    private val lifecycleRegistry by lazy { LifecycleRegistry(this) }

    init {
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    override fun getLifecycle() = lifecycleRegistry

    internal fun onStart() {
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    internal fun onDestroy() {
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }
}