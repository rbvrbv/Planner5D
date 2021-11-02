package su.rbv.planner5d.main.mapper

import su.rbv.planner5d.core_projects.domain.model.ProjectData
import su.rbv.planner5d.main.model.ProjectsAdapterItem

internal class ProjectDataToAdapterMapper : (ProjectData) -> ProjectsAdapterItem {

    override fun invoke(projectData: ProjectData): ProjectsAdapterItem =
        ProjectsAdapterItem(
            projectData = projectData
        )

}