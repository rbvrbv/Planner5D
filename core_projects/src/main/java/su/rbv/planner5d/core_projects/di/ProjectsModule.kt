package su.rbv.planner5d.core_projects.di

import dagger.Binds
import dagger.Module
import su.rbv.planner5d.core_projects.domain.*
import su.rbv.planner5d.core_projects.domain.GetProjectPreviewCaseImpl
import su.rbv.planner5d.core_projects.domain.GetProjectsListCaseImpl

@Module
internal interface ProjectsModule {

    @Binds
    fun bind1(impl: GetProjectByHashCaseImpl): GetProjectByHashCase

    @Binds
    fun bind2(impl: GetProjectsListCaseImpl): GetProjectsListCase

    @Binds
    fun bind3(impl: GetProjectPreviewCaseImpl): GetProjectPreviewCase
}