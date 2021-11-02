package su.rbv.planner5d.core_projects.data.projects.di

import su.rbv.planner5d.shared.di.ComponentHolder

internal object ProjectsRepositoryComponentHolder : ComponentHolder<ProjectsRepositoryComponent>() {

    override val mode = ComponentHolderMode.GLOBAL_SINGLETON

    override fun buildComponent(): ProjectsRepositoryComponent =
            DaggerProjectsRepositoryInternalComponent.builder().build()
}