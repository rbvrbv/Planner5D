package su.rbv.planner5d.core_projects.data.net.di

import su.rbv.planner5d.shared.di.ComponentHolder
import su.rbv.planner5d.shared_android.di.AppComponentHolder

internal object NetRepositoryComponentHolder : ComponentHolder<NetRepositoryComponent>() {

    override val mode = ComponentHolderMode.GLOBAL_SINGLETON

    override fun buildComponent(): NetRepositoryComponent =
            DaggerNetRepositoryInternalComponent.builder()
                .appComponent(AppComponentHolder.getComponent())
                .build()
}