package su.rbv.planner5d.preview.di

import su.rbv.planner5d.core_projects.di.ProjectsComponentHolder
import su.rbv.planner5d.shared.di.ComponentHolder

internal object PreviewFragmentComponentHolder : ComponentHolder<PreviewFragmentComponent>() {

    override val mode = ComponentHolderMode.ALWAYS_CREATE_NEW

    override fun buildComponent(): PreviewFragmentComponent =
        DaggerPreviewFragmentComponent.builder()
            .projectsComponent(ProjectsComponentHolder.getComponent())
            .build()
}