package su.rbv.planner5d.core_projects.di

import dagger.Component
import su.rbv.planner5d.core_projects.data.db.di.DBRepositoryComponent
import su.rbv.planner5d.core_projects.data.net.di.NetRepositoryComponent
import su.rbv.planner5d.core_projects.data.projects.di.ProjectsRepositoryComponent
import su.rbv.planner5d.core_projects.domain.GetProjectByHashCase
import su.rbv.planner5d.core_projects.domain.GetProjectPreviewCase
import su.rbv.planner5d.core_projects.domain.GetProjectsListCase
import su.rbv.planner5d.shared.di.DIComponent

interface ProjectsComponent : DIComponent {

    val getProjectByHashCase: GetProjectByHashCase
    val getProjectsListCase: GetProjectsListCase
    val getProjectPreviewCase: GetProjectPreviewCase
}

@Component(
    dependencies = [
        DBRepositoryComponent::class,
        NetRepositoryComponent::class,
        ProjectsRepositoryComponent::class,
    ],
    modules = [
        ProjectsModule::class
    ]
)
internal interface ProjectsInternalComponent : ProjectsComponent
