package su.rbv.planner5d.core_projects.di

import su.rbv.planner5d.core_projects.data.db.di.DBRepositoryComponentHolder
import su.rbv.planner5d.core_projects.data.net.di.NetRepositoryComponentHolder
import su.rbv.planner5d.core_projects.data.projects.di.ProjectsRepositoryComponentHolder
import su.rbv.planner5d.shared.di.ComponentHolder

object ProjectsComponentHolder : ComponentHolder<ProjectsComponent>() {

    override val mode = ComponentHolderMode.GLOBAL_SINGLETON

    override fun buildComponent(): ProjectsComponent =
        DaggerProjectsInternalComponent.builder()
            .dBRepositoryComponent(DBRepositoryComponentHolder.getComponent())
            .netRepositoryComponent(NetRepositoryComponentHolder.getComponent())
            .projectsRepositoryComponent(ProjectsRepositoryComponentHolder.getComponent())
            .build()
}