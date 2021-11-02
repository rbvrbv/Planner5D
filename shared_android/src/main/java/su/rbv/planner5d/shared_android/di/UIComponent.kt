package su.rbv.planner5d.shared_android.di

import su.rbv.planner5d.shared.di.DIComponent
import su.rbv.planner5d.shared_android.di.vm.ViewModelFactory
import su.rbv.planner5d.shared_android.ui.events.EventPublisher
import su.rbv.planner5d.shared_android.ui.vm.ArgumentsHandler
import su.rbv.planner5d.shared_android.ui.vm.ExceptionHandler
import su.rbv.planner5d.shared_android.ui.vm.ViewModelCoroutineScope
import su.rbv.planner5d.shared_android.ui.vm.ViewModelLifecycleOwner

interface UIComponent : DIComponent {

    val viewModelFactory: ViewModelFactory
    val exceptionHandler: ExceptionHandler
    val argumentsHandler: ArgumentsHandler
    val viewModelLifecycleOwner: ViewModelLifecycleOwner
    val viewModelCoroutineScope: ViewModelCoroutineScope
    val eventPublisher: EventPublisher
}
