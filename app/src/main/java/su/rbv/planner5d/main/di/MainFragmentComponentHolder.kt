package su.rbv.planner5d.main.di

import su.rbv.planner5d.core_projects.di.ProjectsComponentHolder
import su.rbv.planner5d.shared.di.ComponentHolder

internal object MainFragmentComponentHolder : ComponentHolder<MainFragmentComponent>() {

    override val mode = ComponentHolderMode.ALWAYS_CREATE_NEW

    override fun buildComponent(): MainFragmentComponent =
        DaggerMainFragmentComponent.builder()
            .projectsComponent(ProjectsComponentHolder.getComponent())
            .build()
}