package su.rbv.planner5d.shared_android.ui.vm

import androidx.annotation.CallSuper
import androidx.lifecycle.*
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy
import androidx.navigation.NavDirections
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import su.rbv.planner5d.shared_android.base.BufferLiveData
import su.rbv.planner5d.shared_android.extensions.toNavigationEvent
import su.rbv.planner5d.shared_android.ui.events.EventPublisher
import su.rbv.planner5d.shared_android.ui.events.NavigationEvent
import su.rbv.planner5d.shared_android.ui.events.ViewEvent
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), LifecycleObserver, LifecycleOwner, CoroutineScope {

    private val lifecycleRegistry by lazy { LifecycleRegistry(this) }

    @Inject
    internal lateinit var viewModelLifecycleOwner: ViewModelLifecycleOwner

    @Inject
    internal lateinit var viewModelCoroutineScope: ViewModelCoroutineScope

    @Inject
    internal lateinit var exceptionHandler: ExceptionHandler

    @Inject
    internal lateinit var eventPublisher: EventPublisher

    @Inject
    @PublishedApi
    internal lateinit var argumentsHandler: ArgumentsHandler

    private val _events = BufferLiveData<ViewEvent>()
    val events: LiveData<ViewEvent> by lazy { _events.merge(this, eventPublisher.viewEvent) }

    private val _navEvents = BufferLiveData<NavigationEvent>()
    val navEvents: LiveData<NavigationEvent> by lazy { _navEvents.merge(this, eventPublisher.navEvent) }

    private val supervisorJob = SupervisorJob()

    private val defaultExceptionHandler = CoroutineExceptionHandler { _, exception ->
        handleCoroutineException(exception)
    }

    final override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + supervisorJob + defaultExceptionHandler


    init {
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    @CallSuper
    protected open fun initialize() = Unit

    @Inject
    fun initInject() {
        initialize()
    }

    protected fun postViewEvents(vararg events: ViewEvent) = events.forEach(_events::setValue)

    protected fun postNavEvents(vararg events: NavigationEvent) = events.forEach(_navEvents::setValue)

    protected fun postNavEvents(vararg events: NavDirections) {

        val navigationEvents = events
            .map { it.toNavigationEvent() }
            .toTypedArray()

        postNavEvents(*navigationEvents)
    }

    inline fun <reified Args : NavArgs> navArgs() = NavArgsLazy(Args::class) {
        argumentsHandler.arguments
            ?: throw IllegalStateException("Fragment $this has null arguments")
    }

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected open fun onCreate() {
        exceptionHandler.observeExceptions(this, ::handleCoroutineException)
    }

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected open fun onStart() = viewModelLifecycleOwner.onStart()

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected open fun onResume() = Unit

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected open fun onPause() = Unit

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected open fun onStop() = Unit

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected open fun onDestroy() = Unit

    final override fun getLifecycle(): Lifecycle = lifecycleRegistry

    @CallSuper
    protected open fun handleCoroutineException(exception: Throwable) = Unit

    @CallSuper
    override fun onCleared() {
        super.onCleared()

        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
        viewModelLifecycleOwner.onDestroy()
        viewModelCoroutineScope.cancel()
        supervisorJob.cancel()
    }

}