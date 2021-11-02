package su.rbv.planner5d.main.di

import dagger.Component
import su.rbv.planner5d.core_projects.di.ProjectsComponent
import su.rbv.planner5d.shared_android.di.UIComponent

@MainFragmentScope
@Component(
    dependencies = [
        ProjectsComponent::class
    ],
    modules = [
        MainFragmentModule::class
    ]
)

internal interface MainFragmentComponent : UIComponent