package su.rbv.planner5d.shared_android.extensions

import androidx.lifecycle.ViewModelProvider
import su.rbv.planner5d.shared_android.ui.BaseFragment
import su.rbv.planner5d.shared_android.ui.vm.BaseViewModel

inline fun <reified T : BaseViewModel> BaseFragment<T>.injectViewModel(): Lazy<T> = lazy {
    ViewModelProvider(this, viewModelFactory).get(T::class.java)
}
