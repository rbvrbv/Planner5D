package su.rbv.planner5d.shared_android.ui.vm

import androidx.lifecycle.LifecycleOwner
import dagger.Reusable
import kotlinx.coroutines.CoroutineExceptionHandler
import su.rbv.planner5d.shared_android.base.BufferLiveData
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@Reusable
class ExceptionHandler @Inject constructor() {

    val context: CoroutineContext
        get() = exceptionHandler

    private val exceptionsLiveData =
        BufferLiveData<Throwable>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        exceptionsLiveData.postValue(throwable)
    }

    fun observeExceptions(owner: LifecycleOwner, observer: (Throwable) -> Any?) {
        exceptionsLiveData.removeObservers(owner)
        exceptionsLiveData.observe(owner) { it?.let(observer) }
    }
}
