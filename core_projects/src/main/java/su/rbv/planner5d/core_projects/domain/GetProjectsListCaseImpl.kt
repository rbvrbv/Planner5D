package su.rbv.planner5d.core_projects.domain

import su.rbv.planner5d.core_projects.domain.repository.ProjectsRepository
import su.rbv.planner5d.core_projects.domain.model.ProjectData
import javax.inject.Inject

internal class GetProjectsListCaseImpl @Inject constructor(
    private val repository: ProjectsRepository,
) : GetProjectsListCase {

    override suspend fun execute(): List<ProjectData> = repository.getAllProjects()

}