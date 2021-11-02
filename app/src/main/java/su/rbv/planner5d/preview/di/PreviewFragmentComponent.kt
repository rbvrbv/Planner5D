package su.rbv.planner5d.preview.di

import dagger.Component
import su.rbv.planner5d.core_projects.di.ProjectsComponent
import su.rbv.planner5d.shared_android.di.UIComponent

@PreviewFragmentScope
@Component(
    dependencies = [
        ProjectsComponent::class
    ],
    modules = [
        PreviewFragmentModule::class
    ]
)
internal interface PreviewFragmentComponent : UIComponent