package su.rbv.planner5d.core_projects.data.projects.di

import dagger.Binds
import dagger.Module
import su.rbv.planner5d.core_projects.domain.repository.ProjectsRepository
import su.rbv.planner5d.core_projects.data.projects.ProjectsRepositoryImpl
import javax.inject.Singleton

@Module
internal interface ProjectsRepositoryModule {

    @Binds
    @Singleton
    fun bind1(impl: ProjectsRepositoryImpl): ProjectsRepository

}