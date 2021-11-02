package su.rbv.planner5d.shared_android.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T, L : LiveData<T>> Fragment.observeOnViewLifecycle(liveData: L, crossinline body: (T) -> Unit) =
    liveData.observe(this.viewLifecycleOwner, Observer { it?.let(body) })
