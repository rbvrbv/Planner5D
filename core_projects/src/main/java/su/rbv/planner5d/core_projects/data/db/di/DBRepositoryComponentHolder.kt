package su.rbv.planner5d.core_projects.data.db.di

import su.rbv.planner5d.shared.di.ComponentHolder
import su.rbv.planner5d.shared_android.di.AppComponentHolder

internal object DBRepositoryComponentHolder : ComponentHolder<DBRepositoryComponent>() {

    override val mode = ComponentHolderMode.GLOBAL_SINGLETON

    override fun buildComponent(): DBRepositoryComponent =
            DaggerDBRepositoryInternalComponent.builder()
                .appComponent(AppComponentHolder.getComponent())
                .build()
}