package su.rbv.planner5d.core_projects.domain

import su.rbv.planner5d.core_projects.domain.repository.ProjectsRepository
import su.rbv.planner5d.core_projects.domain.model.ProjectData
import javax.inject.Inject

internal class GetProjectByHashCaseImpl @Inject constructor(
    private val repository: ProjectsRepository,
) : GetProjectByHashCase {

    override suspend fun execute(hash: String): ProjectData? = repository.getProjectByHash(hash)

}