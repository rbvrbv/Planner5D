package su.rbv.planner5d.main.model

import su.rbv.planner5d.core_projects.domain.model.ProjectData
import su.rbv.planner5d.shared_android.ui.recycler.DiffListItemModel

data class ProjectsAdapterItem(

    val projectData: ProjectData

) : DiffListItemModel {

    override fun isSameAs(other: DiffListItemModel): Boolean =

        other is ProjectsAdapterItem &&
            other.projectData.hash == projectData.hash &&
            other.projectData.name == projectData.name
}
