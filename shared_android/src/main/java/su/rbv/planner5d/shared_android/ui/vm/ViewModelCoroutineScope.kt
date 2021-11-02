package su.rbv.planner5d.shared_android.ui.vm

import dagger.Reusable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@Reusable
class ViewModelCoroutineScope @Inject constructor(

        private val exceptionHandler: ExceptionHandler

) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + supervisorJob + exceptionHandler.context

    private val supervisorJob = SupervisorJob()

    internal fun cancel() = supervisorJob.cancel()
}
