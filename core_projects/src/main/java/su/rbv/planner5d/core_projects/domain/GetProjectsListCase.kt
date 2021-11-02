package su.rbv.planner5d.core_projects.domain

import su.rbv.planner5d.core_projects.domain.model.ProjectData

interface GetProjectsListCase {

    suspend fun execute(): List<ProjectData>

}