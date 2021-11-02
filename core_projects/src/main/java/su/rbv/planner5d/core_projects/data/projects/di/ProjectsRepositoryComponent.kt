package su.rbv.planner5d.core_projects.data.projects.di

import dagger.Component
import su.rbv.planner5d.core_projects.domain.repository.ProjectsRepository
import su.rbv.planner5d.shared.di.DIComponent
import javax.inject.Singleton

internal interface ProjectsRepositoryComponent : DIComponent {

    val projectsRepository: ProjectsRepository
}

@Singleton
@Component(
    modules = [
        ProjectsRepositoryModule::class
    ]
)
internal interface ProjectsRepositoryInternalComponent : ProjectsRepositoryComponent
